package com.cpf.frame4j.dao;


import com.cpf.frame4j.util.cast.CastUtil;
import com.cpf.frame4j.util.db.EntityUtil;
import com.cpf.frame4j.util.sql.DbSqlUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BaseEntity extends HashMap<String, Object> implements IDbEntity {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseEntity.class);

    /**
     * 数据库表名，如果为空，则通过实体类注解获取表明
     */
    private String tablename = null;

    private String[] id = null;

    /**
     * 记录变更的字段
     */
    private Set<String> modifyFields = null;

    /**
     * 变更标记
     */
    private boolean modifyFlag = true;

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    /**
     * 获取数据库表名，如果为空，则通过实体类注解获取表明
     */
    public String tablename() {
        if (tablename == null) {
            return EntityUtil.getTableName(getClass());
        } else {
            return tablename;
        }
    }

    public String[] id() {
        if (id == null) {
            return EntityUtil.getIds(getClass());
        } else {
            return id;
        }
    }

    protected Set<String> getModifyFields() {
        return modifyFields;
    }

    /**
     * 忽略变更标记
     */
    public void ignoreModifyFlag() {
        this.modifyFlag = false;
    }

    @Override
    public void init(Object dataSource) {
        Map<String, Object> map = (Map<String, Object>) dataSource;
        this.putAll(map);
        // 初始化默认开启变更标记
        this.modifyFlag = true;
    }

    /**
     * generate insert Sql
     */
    protected Object[] generateInsertSql() {
        // TODO 考虑将字段加入注解中
        List<String> fields = new ArrayList<String>();
        List<Object> params = new ArrayList<Object>();
        for (Map.Entry<String, Object> entry : this.entrySet()) {
            fields.add(entry.getKey());
            params.add(entry.getValue());
        }
        // TODO 判断
        if (fields.isEmpty()) {
            LOGGER.error("未找到fields字段，请检查！");
            throw new RuntimeException("未找到fields字段，请检查！");
        }
        // TODO 考虑主键
        // 生成Sql
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ");
        sql.append(tablename());
        sql.append(" (");
        sql.append(StringUtils.join(fields, ","));
        sql.append(") values (");
        sql.append(DbSqlUtil.generateSqlQuestionMarks(fields.size()));
        sql.append(")");
        return new Object[]{sql.toString(), params.toArray()};
    }

    /**
     * generate delete Sql
     */
    protected Object[] generateDeleteSql() {
        // TODO 考虑将字段加入注解中
        List<String> fields = new ArrayList<String>();
        // TODO 判断
        String tName = tablename();
        String primaryKey = id()[0];
        Object primaryVal = get(primaryKey);
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
        // TODO 考虑主键
        return new Object[]{sql.toString(), new Object[]{primaryVal}};
    }

    /**
     * generate update Sql
     */
    protected Object[] generateUpdateSql() {
        Set<String> fields = null;
        List<Object> params = null;
        if (modifyFlag) {
            fields = modifyFields;
        } else {
            // TODO 获取数据库 fields
            // TODO 考虑将字段加入注解中
            fields = this.keySet();
        }
        // TODO 判断
        if (fields == null || fields.isEmpty()) {
            LOGGER.error("未找到fields字段，请检查！");
            throw new RuntimeException("未找到fields字段，请检查！");
        }
        params = new ArrayList<Object>(fields.size());
        // 生成Sql
        StringBuilder sql = new StringBuilder();
        sql.append("update ");
        sql.append(tablename());
        sql.append(" set ");
        boolean firstEle = true;
        for (String field : fields) {
            if (firstEle) {
                firstEle = false;
            } else {
                sql.append(",");
            }
            sql.append(field);
            sql.append("=?");
            // 获取对应值
            params.add(get(field));
        }
        sql.append(" WHERE ");
        String primaryKey = id()[0];
        sql.append(primaryKey);
        sql.append("=?");
        params.add(get(primaryKey));
        return new Object[]{sql.toString(), params.toArray()};
    }


    /* 数据类型的set，get方法*/

    public void set(String field, Object obj) {
        field = field.toLowerCase();
        if (modifyFlag) {
            if (modifyFields == null) {
                // TODO 获取大小
                modifyFields = new HashSet<String>();
            }
            modifyFields.add(field);
        }
        this.put(field, obj);
    }

    public void setStr(String field, String str) {
        set(field, str);
    }

    public String getStr(String field) {
        Object obj = this.get(field);
        return obj == null ? null : obj.toString();
    }

    public void setInteger(String field, Integer inte) {
        set(field, inte);
    }

    public Integer getInteger(String field) {
        Object obj = this.get(field);
        return obj == null ? null : CastUtil.parseInt(obj);
    }

    public void setBoolean(String field, Boolean bool) {
        set(field, bool);
    }

    public Boolean getBoolean(String field) {
        return Boolean.parseBoolean(getStr(field));
    }

    public void setDouble(String field, Double dou) {
        set(field, dou);
    }

    public Double getDouble(String field) {
        return Double.parseDouble(getStr(field));
    }

    public void setDate(String field, Date date) {
        set(field, date);
    }

    public Date getDate(String field) {
        return CastUtil.parse(getStr(field), Date.class);
    }

}
