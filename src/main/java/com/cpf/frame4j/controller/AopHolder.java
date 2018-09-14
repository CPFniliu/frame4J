package com.cpf.frame4j.controller;

import com.cpf.frame4j.annotation.Aspect;
import com.cpf.frame4j.aop.AspectProxy;
import com.cpf.frame4j.aop.ProxyManager;
import com.cpf.frame4j.inter.IProxy;
import com.cpf.frame4j.util.validate.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;

public final class AopHolder {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopHolder.class);

    static {
        Map<Class<? extends AspectProxy>, Set<Class<?>>> proxyMap = creatProxyMap();
        Map<Class<?>, List<IProxy>> targetMap = createTargetMap(proxyMap);
        for (Map.Entry<Class<?>, List<IProxy>> targetEntry : targetMap.entrySet()) {
            Class<?> targetClass = targetEntry.getKey();
            List<IProxy> targetInstances = targetEntry.getValue();
            Object proxy = ProxyManager.creatProxy(targetClass, targetInstances);
            BeanHolder.addBean(targetClass, proxy);
        }

    }


    /**
     * 通过注解对象获取注解对象注解的value对应的注解所对应的所有类
     *
     * @param aspect 注解对象
     * @return
     */
    private static Set<Class<?>> creatTargetClassSet(Aspect aspect) {
        Set<Class<?>> targetClassSet = new HashSet<>();
        Class<? extends Annotation> annotation = aspect.value();
        if (annotation != null && !annotation.equals(Aspect.class)) {
            targetClassSet.addAll(ClassHolder.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    /**
     * 代理类和目标类之间的映射关系(继承了AspectProxy，同时拥有Aspect注解的类)
     * eg : ControllerAspect - ？ annotation FController
     * @return
     */
    private static Map<Class<? extends AspectProxy>, Set<Class<?>>> creatProxyMap() {
        // 存放继承了AspectProxy，同时拥有Aspect注解的类的集合
        Map<Class<? extends AspectProxy>, Set<Class<?>>> proxyMap = new HashMap<>();
        // 获取继承了AspectProxy的类的集合（代理类集合）
        Set<Class<? extends AspectProxy>> proxySet = ClassHolder.getClassSetBySuper(AspectProxy.class);
        for (Class<? extends AspectProxy> proxyClass : proxySet) {
            if (! proxyClass.isAnnotationPresent(Aspect.class)) {
                LoggerUtil.loggerAndThrow(proxyClass.getName()
                        + " is extends the AspectProxy.class, but it has not Annotation of Aspect! please check");
            }
            // 获取每一个对应类的注解对象
            Aspect aspect = proxyClass.getAnnotation(Aspect.class);
            // 通过注解对象查询注解对应value对应的注解对应的目标对象
            Set<Class<?>> targetClassSet = creatTargetClassSet(aspect);
            proxyMap.put(proxyClass, targetClassSet);
        }
        return proxyMap;
    }

    /**
     * 获取代理目标类与代理类对象实体集合的映射关系
     * eg : ？ annotation FController - ControllerAspect
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static Map<Class<?>, List<IProxy>> createTargetMap(Map<Class<? extends AspectProxy>, Set<Class<?>>> proxyMap) {
        Map<Class<?>, List<IProxy>> targetMap = new HashMap<>();
        for (Map.Entry<Class<? extends AspectProxy>, Set<Class<?>>> entry : proxyMap.entrySet()) {
            Class<? extends AspectProxy> proxyClass = entry.getKey();
            Set<Class<?>> targetClassSet = entry.getValue();
            for (Class<?> targetCls : targetClassSet) {
                AspectProxy aspectProxy = null;
                try {
                    aspectProxy = proxyClass.newInstance();
                } catch (Exception e) {
                    LoggerUtil.loggerAndThrow(LOGGER, "new Instance 失败", e);
                }
                if (targetMap.containsKey(targetCls)) {
                    targetMap.get(targetCls).add(aspectProxy);
                } else {
                    List<IProxy> list = new ArrayList<>();
                    list.add(aspectProxy);
                    targetMap.put(targetCls, list);
                }
            }
        }
        return targetMap;
    }

}
