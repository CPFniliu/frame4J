package com.cpfframe4j.util.cast;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class CastUtil {

    public static Integer parseInt(Object obj, Integer defaultValue) {
        int value = 0;
        if (obj != null) {
            String str = String.valueOf(obj);
            if (StringUtils.isNotBlank(str)) {
                try {
                    value = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }


    public static int parseInt(Object obj) {
        return parseInt(obj, 0);
    }

    public static Integer parseInteger(Object obj) {
        return parseInt(obj, null);
    }


    public static Date parseDate(Object obj) {
        return parse(obj, Date.class);
    }


    public static Boolean parseBoolean(Object obj) {
        return parse(obj, Boolean.class);
    }

    public static <T> T parse(Object obj, Class<T> primaryClass) {
        if (primaryClass.isInstance(obj)) {
            return (T)obj;
        }
        return null;
    }


}
