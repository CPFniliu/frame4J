package com.cpf.frame4j.controller;

import com.cpf.frame4j.annotation.FInject;
import com.cpf.frame4j.util.common.ReflectionUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanHolder {

    public static final Logger LOGGER = LoggerFactory.getLogger(BeanHolder.class);

    public static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        // 初始化Bean
        Set<Class<?>> beanClassSet = ClassHolder.getServiceAndControllerClassSet();
        if(CollectionUtils.isEmpty(beanClassSet)) {
            throw new RuntimeException("初始化Bean为空！ 请核实！");
        }
        LOGGER.debug("BEAN 实例化开始");
        for (Class<?> beanClass : beanClassSet) {
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, obj);
        }
        LOGGER.debug("BEAN 实例化结束");
        LOGGER.debug("开始对实例化的Bean种相应字段进行依赖注入");
        // 依赖注入
        for (Map.Entry<Class<?>, Object> entry : BEAN_MAP.entrySet()) {
            Class<?> beanClass = entry.getKey();
            Object beanInstance = entry.getValue();
            Field[] fields = beanClass.getDeclaredFields();
            if (ArrayUtils.isNotEmpty(fields)) {
                for (Field field : fields) {
                    if (field.isAnnotationPresent(FInject.class)) {
                        Class<?> beanFieldCls = field.getType();
                        Object beanFieldInstance = BEAN_MAP.get(beanFieldCls);
                        if (beanFieldCls == null) {
                            throw new RuntimeException("初始化以来注入时异常，找不到相关类 " +  beanFieldCls.getName());
                        } else {
                            ReflectionUtil.setField(beanInstance, field, beanFieldInstance);
                        }
                    }
                }
            }
        }
        LOGGER.debug("Bean中相应字段进行依赖注入结束");
    }

    //
    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("not found " + cls.getName() + "in BEAN_MAP");
        }
        return (T) BEAN_MAP.get(cls);
    }

    public static void addBean(Class<?> cls, Object obj) {
        BEAN_MAP.put(cls, obj);
    }

}
