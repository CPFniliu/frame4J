//package com.cpfframe4j.util.sql;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import com.epoint.core.utils.date.EpointDateUtil;
//import com.epoint.core.utils.string.StringUtil;
//
//public class SqlUtils {
//
//    // sql语句比较符
//    public static final String STR_ONE = "1"; //等于
//    public static final String SQL_OPERATOR_EQ = "eq"; //等于
//    public static final String SQL_OPERATOR_NQ = "nq"; //不等于
//    public static final String SQL_OPERATOR_GT = "gt"; //大于
//    public static final String SQL_OPERATOR_GE = "ge"; //大于等于
//    public static final String SQL_OPERATOR_LT = "lt"; //小于
//    public static final String SQL_OPERATOR_LE = "le"; //小于等于
//    public static final String SQL_OPERATOR_LIKE = "like"; //like
//    public static final String SQL_OPERATOR_IN = "in"; //in
//    public static final String SQL_OPERATOR_NOTIN = "noin"; //in
//    public static final String SQL_OPERATOR_BETWEEN = "btw"; //between
//    public static final String SQL_OPERATOR_ISNULL = "null"; //为空
//    public static final String SQL_OPERATOR_ISNOTNULL = "notn"; //不为空
//
//    // 政务服务所用的分割符
//    public static final String SQL_OPERATE_SPLIT = "#SOS#";
//
//	private Map<String, String> conditionMap = new HashMap<>();
//
//	public SqlUtils() {
//		// 设置标记，用于兼容当前模式
//		conditionMap.put("#isnew", STR_ONE);
//	}
//
//	public SqlUtils(Map<String, String> map) {
//		if (map != null && STR_ONE.equals(map.get("#isnew"))) {
//			conditionMap.putAll(map);
//		} else {
//			conditionMap.put("#isnew", STR_ONE);
//		}
//	}
//
//	/**
//	 * 等于
//	 * @param fieldName 字段名
//	 * @param fieldValue 字段值
//	 */
//	public void eq(String fieldName, String fieldValue) {
//		conditionMap.put(
//				fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_EQ + SQL_OPERATE_SPLIT + "S",
//				fieldValue);
//	}
//
//	/**
//	 * 不等于
//	 * @param fieldName 字段名
//	 * @param fieldValue 字段值
//	 */
//	public void nq(String fieldName, String fieldValue) {
//		conditionMap.put(
//				fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_NQ + SQL_OPERATE_SPLIT + "S",
//				fieldValue);
//	}
//
//	/**
//	 * 大于
//	 * @param fieldName 字段名
//	 * @param fieldValue 字段值
//	 */
//	public void gt(String fieldName, String fieldValue) {
//		conditionMap.put(
//				fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_GT + SQL_OPERATE_SPLIT + "S",
//				fieldValue);
//	}
//
//	/**
//	 * 时间类型大于
//	 * @param fieldName 字段名
//	 * @param fieldValue 字段值
//	 */
//	public void gt(String fieldName, Date fieldValue) {
//		conditionMap.put(
//				fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_GT + SQL_OPERATE_SPLIT + "D",
//				EpointDateUtil.convertDate2String(fieldValue, EpointDateUtil.DATE_TIME_FORMAT));
//	}
//
//	/**
//	 * 大于等于
//	 * @param fieldName 字段名
//	 * @param fieldValue 字段值
//	 */
//	public void ge(String fieldName, String fieldValue) {
//		conditionMap.put(
//				fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_GE + SQL_OPERATE_SPLIT + "S",
//				fieldValue);
//	}
//
//	/**
//	 * 时间类型大于等于
//	 * @param fieldName 字段名
//	 * @param fieldValue 字段值
//	 */
//	public void ge(String fieldName, Date fieldValue) {
//		conditionMap.put(
//				fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_GE + SQL_OPERATE_SPLIT + "D",
//				EpointDateUtil.convertDate2String(fieldValue, EpointDateUtil.DATE_TIME_FORMAT));
//	}
//
//	/**
//	 * 小于
//	 * @param fieldName 字段名
//	 * @param fieldValue 字段值
//	 */
//	public void lt(String fieldName, String fieldValue) {
//		conditionMap.put(
//				fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_LT + SQL_OPERATE_SPLIT + "S",
//				fieldValue);
//	}
//
//	/**
//	 * 时间类型小于
//	 * @param fieldName 字段名
//	 * @param fieldValue 字段值
//	 */
//	public void lt(String fieldName, Date fieldValue) {
//		conditionMap.put(
//				fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_LT + SQL_OPERATE_SPLIT + "D",
//				EpointDateUtil.convertDate2String(fieldValue, EpointDateUtil.DATE_TIME_FORMAT));
//	}
//
//	/**
//	 * 小于等于
//	 * @param fieldName 字段名
//	 * @param fieldValue 字段值
//	 */
//	public void le(String fieldName, String fieldValue) {
//		conditionMap.put(
//				fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_LE + SQL_OPERATE_SPLIT + "S",
//				fieldValue);
//	}
//
//	/**
//	 * 时间类型小于等于
//	 * @param fieldName 字段名
//	 * @param fieldValue 字段值
//	 */
//	public void le(String fieldName, Date fieldValue) {
//		conditionMap.put(
//				fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_LE + SQL_OPERATE_SPLIT + "D",
//				EpointDateUtil.convertDate2String(fieldValue, EpointDateUtil.DATE_TIME_FORMAT));
//	}
//
//	/**
//	 * like
//	 * @param fieldName 字段名
//	 * @param fieldValue 字段值
//	 */
//	public void like(String fieldName, String fieldValue) {
//		conditionMap.put(
//				fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_LIKE + SQL_OPERATE_SPLIT + "S",
//				fieldValue);
//	}
//
//	/**
//	 * in
//	 * @param fieldName 字段名
//	 * @param fieldValue 字段值,括号中的内容
//	 */
//	public void in(String fieldName, String fieldValue) {
//		conditionMap.put(
//				fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_IN + SQL_OPERATE_SPLIT + "S",
//				fieldValue);
//	}
//
//	/**
//	 * not in
//	 * @param fieldName 字段名
//	 * @param fieldValue 字段值，括号中的内容
//	 */
//	public void notin(String fieldName, String fieldValue) {
//		conditionMap.put(
//				fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_NOTIN + SQL_OPERATE_SPLIT + "S",
//				fieldValue);
//	}
//
//	/**
//	 * between,只用于时间类型
//	 * @param fieldName 字段名
//	 * @param fromDate	开始时间
//	 * @param endDate	结束时间
//	 */
//	public void between(String fieldName, Date fromDate, Date endDate) {
//		conditionMap.put(
//				fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_BETWEEN + SQL_OPERATE_SPLIT + "D",
//				EpointDateUtil.convertDate2String(fromDate, EpointDateUtil.DATE_TIME_FORMAT) + SQL_OPERATE_SPLIT
//						+ EpointDateUtil.convertDate2String(endDate, EpointDateUtil.DATE_TIME_FORMAT));
//	}
//
//	/**
//	 * 为空
//	 * @param fieldName 字段名
//	 */
//	public void isBlank(String fieldName) {
//		isBlankOrValue(fieldName,"");
//	}
//
//	/**
//	 * 为空或者等于某个值
//	 * @param fieldName 字段名
//	 * @param fieldValue 等于的值
//	 */
//	public void isBlankOrValue(String fieldName, String fieldValue) {
//		conditionMap.put(
//				fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_ISNULL + SQL_OPERATE_SPLIT + "S",
//				fieldValue);
//	}
//
//	/**
//	 * 不为空
//	 * @param fieldName 字段名
//	 */
//	public void isNotBlank(String fieldName) {
//		conditionMap.put(fieldName + SQL_OPERATE_SPLIT + SQL_OPERATOR_ISNOTNULL
//				+ SQL_OPERATE_SPLIT + "S", "");
//	}
//
//	/**
//	 * 设置倒序排列的字段
//	 * @param fieldName
//	 */
//	public void setOrderDesc(String fieldName) {
//		setOrder(fieldName,"desc");
//	}
//
//	/**
//	 * 设置正序排列的字段
//	 * @param fieldName
//	 */
//	public void setOrderAsc(String fieldName) {
//		setOrder(fieldName,"asc");
//	}
//
//	/**
//	 * 设置排序字段和方向
//	 * @param sortField 字段名
//	 * @param sortOrder 排序方向（asc,desc）
//	 */
//	public void setOrder(String sortField, String sortOrder) {
//		String sort = conditionMap.get("#sort");
//		if (StringUtil.isBlank(sort))
//			conditionMap.put("#sort", sortField + " " + sortOrder);
//		else
//			conditionMap.put("#sort", sort + "," + sortField + " " + sortOrder);
//	}
//
//	/**
//	 * 设置查询数目,只在查询List时有效
//	 * @param count 查询数目
//	 */
//	public void setSelectCounts(Integer count) {
//		conditionMap.put("#count", count.toString());
//	}
//
//	/**
//	 * 需要查询的字段
//	 * @param fields select的字段
//	 */
//	public void setSelectFields(String fields) {
//		conditionMap.put("#fields", fields);
//	}
//
//	/**
//	 * 左连接表，此时原表默认别名为 a
//	 * @param tableName 表名
//	 * @param leftTableField 左侧表关联的字段
//	 * @param rightTableField 右侧表关联的字段
//	 */
//	public void setLeftJoinTable(String tableName, String leftTableField, String rightTableField){
//		conditionMap.put("#join",
//				"#left#" + (StringUtil.isBlank(conditionMap.get("#join")) ? "" : conditionMap.get("#join")) + tableName
//						+ "#" + leftTableField + "#" + rightTableField + ";");
//	}
//
//	/**
//	 * 右连接表，此时原表默认别名为 a
//	 * @param tableName 表名
//	 * @param leftTableField 左侧表关联的字段
//	 * @param rightTableField 右侧表关联的字段
//	 */
//	public void setRightJoinTable(String tableName, String leftTableField, String rightTableField){
//		conditionMap.put("#join",
//				"#right#" + (StringUtil.isBlank(conditionMap.get("#join")) ? "" : conditionMap.get("#join")) + tableName
//						+ "#" + leftTableField + "#" + rightTableField + ";");
//	}
//
//	/**
//	 * 内连接表，此时原表默认别名为 a
//	 * @param tableName 表名
//	 * @param leftTableField 左侧表关联的字段
//	 * @param rightTableField 右侧表关联的字段
//	 */
//	public void setInnerJoinTable(String tableName, String leftTableField, String rightTableField){
//		conditionMap.put("#join",
//				"#inner#" + (StringUtil.isBlank(conditionMap.get("#join")) ? "" : conditionMap.get("#join")) + tableName
//						+ "#" + leftTableField + "#" + rightTableField + ";");
//	}
//
//	public Map<String, String> getMap() {
//		return conditionMap;
//	}
//
//	/**
//	 * 清除条件
//	 */
//	public void clear(){
//		conditionMap.clear();
//		conditionMap.put("#isnew", STR_ONE);
//	}
//}
