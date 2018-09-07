//package com.cpfframe4j.service;
//
//import com.cpfframe4j.dao.DataSourceConfig;
//import com.cpfframe4j.dao.FrameDao;
//import com.cpfframe4j.dao.IDao;
//
//public class CommonService {
//
//    /**
//     * 数据增删改查组件
//     */
//    protected IDao baseDao;
//
//    public ConvenientService() {
//        baseDao = FrameDao.getInstance();
//    }
//
//    public ConvenientService(IDao dao) {
//        baseDao = dao;
//    }
//
//    public ConvenientService(DataSourceConfig dataSourceConfig) {
//        baseDao = FrameDao.getInstance(dataSourceConfig);
//    }
//
//    /**
//     *  获取Dao
//     */
//    public IDao getDao() {
//        return baseDao;
//    }
//
//    /**
//     *  开始事务
//     */
//    public void beginTransaction() {
//        baseDao.beginTransaction();
//    }
//
//    /**
//     *  提交
//     */
//    public void commitTransaction() {
//        baseDao.commitTransaction();
//    }
//
//    /**
//     *  回滚事务
//     */
//    public void rollBackTransaction() {
//        baseDao.rollBackTransaction();
//    }
//
//    /**
//     *  关闭Dao
//     */
//    public void close() {
//        baseDao.close();
//    }
//
//
//    /**
//     *  ############################################################
//     *  增删改
//     *  ############################################################
//     */
//
//    /**
//     * ================================================================
//     *  增加数据
//     */
//    public int insert(Record record) {
//        return baseDao.insert(record);
//    }
//
//    /**
//     * ================================================================
//     * 删除数据 BaseEntity
//     *
//     *  @param returnCalss
//     *  @param guid
//     *  @return
//     */
//    public <T extends BaseEntity> int deleteByGuid(Class<T> basicCalss, String guid) {
//        T t = baseDao.find(basicCalss, guid);
//        return baseDao.delete(t);
//    }
//
//    /**
//     * 根据字段删除
//     *
//     * @param baseClass
//     * @param keyValue
//     * @param key
//     */
//    public int deleteRecords(Class<? extends BaseEntity> baseClass, String field, String value) {
//        String tablename = SqlBuilder.getTablenameByClass(baseClass);
//        String sql = "DELETE FROM " + tablename + " WHERE " + field + " = ?1";
//        return baseDao.execute(sql, value);
//    }
//
//
//    /**
//     * 批量删除
//     *
//     *  @param baseClass
//     *  @param values
//     *  @return
//     */
//    public int deleteRecords(Class<? extends BaseEntity> baseClass, String... values) {
//        Entity en = SqlBuilder.getEntityByClass(baseClass);
//        String sql = "DELETE FROM " + en.table() + " WHERE " + en.id() + " IN ('" + StringUtils.join(values, "','") + "')";
//        return baseDao.execute(sql);
//    }
//
//    /**
//     * 批量删除
//     *
//     *  @param field
//     *  @param baseClass
//     *  @param values
//     *  @return
//     */
//    public int deleteRecords(String field, Class<? extends BaseEntity> baseClass, String[] values) {
//        String tablename = SqlBuilder.getTablenameByClass(baseClass);
//        String sql = "DELETE FROM " + tablename + " WHERE " + field + " IN ('" + StringUtils.join(values, "','") + "')";
//        return baseDao.execute(sql);
//    }
//
//    /**
//     * ================================================================
//     *  更新数据
//     */
//    public int update(Record record) {
//        return baseDao.update(record);
//    }
//
//    /**
//     *  执行语句
//     */
//    public int excute(String sql, Object... args) {
//        return baseDao.execute(sql, args);
//    }
//
//    /**
//     *  ############################################################
//     *  基本查询
//     *  ############################################################
//     */
//
//    /**
//     * ================================================================
//     *  查询单个数据
//     */
//
//    public <T extends Record> T find(String sql, Class<T> returnCalss, Object... args) {
//        return baseDao.find(sql, returnCalss, args);
//    }
//
//    public <T extends BaseEntity> T find(Class<T> returnCalss, Object args) {
//        Entity en = SqlBuilder.getEntityByClass(returnCalss);
//        return baseDao.find(SqlBuilder.generateSql("*", returnCalss, "AND " + en.id()[0] + " = ?"), returnCalss, args);
//    }
//
//
//    public <T extends BaseEntity> T find(Class<T> returnCalss, String field, Object args) {
//        return baseDao.find(SqlBuilder.generateSql("*", returnCalss, "AND " + field + " = ?"), returnCalss, args);
//    }
//
//    public Record find(String sql, Object... args) {
//        return find(sql, Record.class, args);
//    }
//
//    /**
//     *  通过某个数据表某个数据的值查询某条数据
//     */
//    public <T extends BaseEntity> T find(Class<T> baseClass, String selectField, String field, Object objs) {
//        return find(SqlBuilder.generateSql(baseClass, selectField, generateSss(field), null, null), baseClass, objs);
//    }
//
//    /**
//     * ================================================================
//     *  查询列表数据
//     */
//    public List<Record> findList(String sql, Object... args) {
//        return baseDao.findList(sql, Record.class, args);
//    }
//
//    public <T extends Record> List<T> findList(String sql, Class<T> returnCalss, Object... args) {
//        return baseDao.findList(sql, returnCalss, args);
//    }
//
//
//    /**
//     *  分页查询列表数据
//     */
//    public List<Record> findList(String sql, int first, int pageSize, Object... args) {
//        return baseDao.findList(sql, first, pageSize, Record.class, args);
//    }
//
//    public <T extends Record> List<T> findList(String sql, int first, int pageSize, Class<T> returnCalss, Object... args) {
//        return baseDao.findList(sql, first, pageSize, returnCalss, args);
//    }
//
//    /**
//     * ================================================================
//     *  获取 boolean 类型数据
//     */
//    public boolean queryBoolean(String sql, Object... objs) {
//        return baseDao.queryBoolean(sql, objs);
//    }
//
//    /**
//     *  判断相关表相关字段是否有相应数据
//     */
//    public boolean hasData(String tablename, String field, Object value) {
//        return baseDao.queryBoolean(SqlBuilder.generateCountSql(tablename, generateSss(field)), value);
//    }
//
//    public boolean hasData(Class<? extends BaseEntity> baseClass, String field, Object value) {
//        return baseDao.queryBoolean(SqlBuilder.generateCountSql(baseClass, generateSss(field)), value);
//    }
//
//    /**
//     * ================================================================
//     *  获取 Integer 类型数据
//     */
//    public int queryInt(String sql, Object... objs) {
//        return baseDao.queryInt(sql, objs);
//    }
//
//    public int queryInt(Class<? extends BaseEntity> baseClass, String conditionSql, Object... objs) {
//        return queryInt(SqlBuilder.generateCountSql(baseClass, conditionSql), objs);
//    }
//
//    /**
//     *  ================================================================
//     *  获取 String 类型数据
//     */
//    public String queryString(String sql, Object... objs) {
//        return baseDao.queryString(sql, objs);
//    }
//
//    /**
//     *  获取某个数据表某个数据的值
//     */
//    public String queryString(Class<? extends BaseEntity> baseClass, String selectField, String field, Object objs) {
//        return baseDao.queryString(SqlBuilder.generateSql(baseClass, selectField, generateSss(field), null, null), objs);
//    }
//
//
//    public String generateSss(String field){
//        return "AND " + field + " = ?";
//    }
//
//
//
//}
