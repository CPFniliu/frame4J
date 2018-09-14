package com.cpf.frame4j.dao;

import com.cpf.frame4j.util.db.EntityUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FrameDao implements IDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(FrameDao.class);

    private Connection conn = null;

    private QueryRunner queryRunner = null;

    private FrameDao(Connection conn) {
        this.conn = conn;
        queryRunner = new QueryRunner();
    }

    public static FrameDao getInstance(){
        Connection connection = DbConnection.getConnection();
        return new FrameDao(connection);
    }

    @Override
    public boolean insert(IDbEntity entity) {
        int result = 0;
        if (BaseEntity.class.isInstance(entity)) {
            BaseEntity baseEntity = (BaseEntity)entity;
            Object[] sqlAndParams = baseEntity.generateInsertSql();
            Object[] params = (Object[]) sqlAndParams[1];
            result = exeQueryRunnerUpdate(sqlAndParams[0].toString(), params);
        }
        return result > 0;
    }

    private int delete(String tName, String primaryKey, Object primaryVal) {
        // 判断
        if (StringUtils.isAnyBlank(tName, primaryKey) || primaryVal == null) {
            String err = "生成delete语句错误，## tname ：" + tName + ", primaryKey : " + primaryKey + ", primaryVal : " + primaryVal.toString();
            LOGGER.error(err);
            throw new RuntimeException(err);
        }
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(tName);
        sql.append(" WHERE ");
        sql.append(primaryKey);
        sql.append(" = ?");
        return exeQueryRunnerUpdate(sql.toString(), primaryVal);
    }

    @Override
    public int delete(IDbEntity entity) {
        String primaryKey = entity.id()[0];
        return delete(entity.tablename(), primaryKey, entity.get(primaryKey));
    }

    @Override
    public int delete(Class<? extends IDbEntity> tableClass, Object primaryVal) {
        String[] tNameAndIds = EntityUtil.getTableNameAndIds(tableClass);
        if (tNameAndIds == null) {
            String err = tableClass.getName() + " 获取tableName失败，请验证其是否加入了DbTable注解";
            LOGGER.error(err);
            throw new RuntimeException(err);
        }
        return delete(tNameAndIds[0], tNameAndIds[1], primaryVal);
    }

    @Override
    public int update(IDbEntity entity) {
        int result = 0;
        if (BaseEntity.class.isInstance(entity)) {
            BaseEntity baseEntity = (BaseEntity)entity;
            Object[] sqlAndParams = baseEntity.generateUpdateSql();
            Object[] params = (Object[]) sqlAndParams[1];
            result = exeQueryRunnerUpdate(sqlAndParams[0].toString(), params);
        }
        return result;
    }

    @Override
    public <T extends IDbEntity> T find(String sql, Class<T> retrunClass, Object... params) {
        if (BaseEntity.class.isAssignableFrom(retrunClass)) {
            Map<String, Object> queryMap = exeQueryRunnerQuery(conn, sql, new MapHandler(), params);
            try {
                T entity = retrunClass.newInstance();
                entity.init(queryMap);
                return entity;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public <T extends IDbEntity> List<T> findList(String sql, Class<T> retrunClass, Object... params) {
        List<T> entityList = null;
        if (BaseEntity.class.isAssignableFrom(retrunClass)) {
            List<Map<String, Object>> mapList = exeQueryRunnerQuery(conn, sql, new MapListHandler(), params);
            // 将查找出的数据封装为对应的实体类
            entityList = new ArrayList<T>(mapList.size());
            for (Map<String, Object> map : mapList){
                try {
                    T entity = retrunClass.newInstance();
                    entity.init(map);
                    entityList.add(entity);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
//        else if (){
//            entityList = queryRunner.query(conn, sql, new BeanListHandler<T>(baseClass), params);
//        }
        return entityList;
    }


    /**
     * 执行QueryRunner.update操作
     * @param sql
     * @param params
     * @return 执行结果
     */
    private int exeQueryRunnerUpdate(String sql, Object... params) {
        LOGGER.error("Update  ## sql ：" + sql + ", params :" + params.toString());
        try {
            return queryRunner.update(conn, sql, params);
        } catch (SQLException e) {
            LOGGER.error("update failure  ## sql ：" + sql + ", params :" + params.toString(), e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 执行QueryRunner.query操作
     * @param sql
     * @param params
     * @return 执行结果
     */
    private <T> T exeQueryRunnerQuery(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params) {
        LOGGER.error("Query  ## sql ：" + sql + ", params :" + params.toString());
        try {
            return queryRunner.query(conn, sql, rsh, params);
        } catch (SQLException e) {
            LOGGER.error("find failure  ## sql ：" + sql + ", params :" + params.toString(), e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void beginTransaction() {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("transaction start failure", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void commitTransaction() {
        try {
            conn.commit();
        } catch (SQLException e) {
            LOGGER.error("transaction commit failure", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rollBackTransaction() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            LOGGER.error("transaction rollback failure", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            LOGGER.error("connection close failure", e);
            throw new RuntimeException(e);
        }
    }

}
