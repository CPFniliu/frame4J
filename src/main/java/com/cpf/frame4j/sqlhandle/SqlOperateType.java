package com.cpf.frame4j.sqlhandle;

public enum SqlOperateType
{
    SQL("sql"), EQ(" = "), NQ(" <> "), GT(" > "), GE(" >= "), LT(" < "), LE(" <= "), LIKE(" like "), IN("in"), NOT_IN(
            "not in"), BETWEEN("between"), IS_NULL("is null"), IS_NON("is not null");

    private final String sign;

    SqlOperateType(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
