package com.cpf.frame4j.dao;

public interface IDbEntity {

    String tablename();

    String[] id();

    void set(String field, Object object);

    Object get(Object key);

    void init(Object dataSource);

}
