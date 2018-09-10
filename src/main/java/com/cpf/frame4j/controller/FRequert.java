package com.cpf.frame4j.controller;

import com.cpf.frame4j.util.config.ERequestType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class FRequert {

    /**
     * get,post
     */
    private ERequestType requestType;

    private String requestPath;

    public FRequert(ERequestType requestType, String requestPath) {
        this.requestType = requestType;
        this.requestPath = requestPath;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return "FRequert{" + "requestType=" + requestType + ", requestPath='" + requestPath + '\'' + '}';
    }
}
