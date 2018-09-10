package com.cpf.frame4j.controller;

import com.cpf.frame4j.annotation.FAction;
import com.cpf.frame4j.util.config.ERequestType;
import com.cpf.frame4j.util.validate.LoggerUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerHolder {

    public static final Map<FRequert, Handler> ACTION_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHolder.getControllerClassSet();
        if (CollectionUtils.isNotEmpty(controllerClassSet)) {
            for (Class<?> controllerClass : controllerClassSet) {
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtils.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(FAction.class)) {
                            FAction fAction = method.getAnnotation(FAction.class);
                            String mapping = fAction.mapping();
                            ERequestType requestType = fAction.type();
                            FRequert requert = new FRequert(requestType, mapping);
                            Handler handler = new Handler(controllerClass, method);
                            if (ACTION_MAP.containsKey(requert)) {
                                LoggerUtil.loggerAndThrow("FRequest 请求重复，" + requert);
                            }
                            ACTION_MAP.put(requert, handler);
                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(ERequestType requestType, String requestPath) {
        FRequert requert = new FRequert(requestType, requestPath);
        return ACTION_MAP.get(requert);
    }

}
