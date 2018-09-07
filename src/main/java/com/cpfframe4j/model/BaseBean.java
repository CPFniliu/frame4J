package com.cpfframe4j.model;

import com.cpfframe4j.dao.BaseEntity;

public class BaseBean extends BaseEntity {

    @Override
    public String tablename() {
        return null;
    }

    @Override
    public String[] id() {
        return new String[0];
    }

    @Override
    public void ignoreModifyFlag() {

    }
}
