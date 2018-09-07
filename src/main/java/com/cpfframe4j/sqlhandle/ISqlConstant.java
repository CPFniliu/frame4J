package com.cpfframe4j.sqlhandle;

public interface ISqlConstant
{


    public static final String COUNT = "#COUNT";
    public static final String FIELDS = "#FIELDS";
    public static final String TABLENAME = "#TABLENAME";
    public static final String _AND_ = " AND ";
    public static final String SQL_OPERATE_SPLIT = "«®®»";
    
    
    public enum OrderDirection{
        ASC, DESC
    }
    
    /**
     *  数据类型
     */
    public enum ValueType
    {
        S, D
    }

    /**
     *  Sql类型
     */
    public enum SqlType
    {
        COUNT, FIELD, DELETE, UPTATE, INSERT
    }
    

    /**
     *  数据库类型
     */
    public enum DBType{
        MYSQL,ORACLE,SQLSEVER
    }
    
    
//    /**
//     *  Sql 操作
//     */
//    public enum SqlOperateType
//    {
//        SQL("sql"), EQ(" = "), NQ(" <> "), GT(" > "), GE(" >= "), LT(" < "), LE(" <= "), LIKE(" like "), IN("in"), NOT_IN(
//                "not in"), BETWEEN("between"), IS_NULL("is null"), IS_NON("is not null");
//
//        private final String sign;
//
//        SqlOperateType(String sign) {
//            this.sign = sign;
//        }
//
//        public String getSign() {
//            return sign;
//        }
//    }
    

    /**
     *  Sql 操作
     */
    public enum SqlOperateType
    {
        SQL, EQ, NQ, GT, GE, LT, LE, LIKE, IN, NOT_IN, BETWEEN, IS_NULL, IS_NON;
    }

}
