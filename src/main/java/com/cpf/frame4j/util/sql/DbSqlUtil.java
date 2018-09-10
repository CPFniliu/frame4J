package com.cpf.frame4j.util.sql;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class DbSqlUtil {

    public static String generateSqlQuestionMarks(int count){
        String[] arr = new String[count];
        Arrays.fill(arr, "?");
        return StringUtils.join(arr, ",");
    }
}
