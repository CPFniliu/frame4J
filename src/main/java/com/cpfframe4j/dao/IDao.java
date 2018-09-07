package com.cpfframe4j.dao;

import java.util.List;

public interface IDao {

    /**
     * 插入实体类
     */
    public boolean insert(IDbEntity entity);

    /**
     * 通过主键删除
     */
    public int delete(Class<? extends IDbEntity> tableClass, Object primaryVal);

    /**
     * 通过实体删除
     */
    public int delete(IDbEntity entity);

    /**
     * 更新
     */
    public int update(IDbEntity entity);

    /**
     * 查找单个实体
     */
    public <T extends IDbEntity> T find(String sql, Class<T> retrunClass, Object... params);

    /**
     * 查找列表实体
     */
    public <T extends IDbEntity> List<T> findList(String sql, Class<T> retrunClass, Object... params);

    /**
     *  开始事务
     */
    public void beginTransaction();

    /**
     *  提交
     */
    public void commitTransaction();

    /**
     *  回滚事务
     */
    public void rollBackTransaction();

    /**
     *  关闭Dao
     */
    public void close();

}
