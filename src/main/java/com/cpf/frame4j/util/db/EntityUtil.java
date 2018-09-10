package com.cpf.frame4j.util.db;

import com.cpf.frame4j.annotation.DbTable;
import com.cpf.frame4j.dao.IDbEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityUtil.class);

    private static DbTable getDbTableAnnotation(Class<? extends IDbEntity> beClass){
        return beClass.getAnnotation(DbTable.class);
    }

    public static String getTableName(Class<? extends IDbEntity> beClass){
        DbTable annotation = beClass.getAnnotation(DbTable.class);
        return annotation == null ? null : annotation.table();
    }

    public static String[] getIds(Class<? extends IDbEntity> beClass){
        DbTable annotation = beClass.getAnnotation(DbTable.class);
        return annotation == null ? null : annotation.id();
    }

    public static String[] getTableNameAndIds(Class<? extends IDbEntity> beClass){
        DbTable dbTableAnnotation = getDbTableAnnotation(beClass);
        if (dbTableAnnotation == null) {
            return null;
        }
        String[] ids = dbTableAnnotation.id();
        if (ids == null) {
            return new String[]{dbTableAnnotation.table()};
        }
        int idlen = ids.length;
        String[] tablenameAndIds = new String[idlen + 1];
        tablenameAndIds[0] = dbTableAnnotation.table();
        System.arraycopy(ids,0,tablenameAndIds,1,idlen);
        return tablenameAndIds;
    }

}
