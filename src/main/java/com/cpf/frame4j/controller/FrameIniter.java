package com.cpf.frame4j.controller;

        import com.cpf.frame4j.util.common.ClassUtil;

public class FrameIniter {

    public static void init() {
        Class<?>[] classes = {ClassHolder.class,
                BeanHolder.class,
                AopHolder.class,
                ControllerHolder.class,
        };

        for (Class<?> cls : classes) {
            ClassUtil.loadClass(cls.getName(), false);
        }
    }


    public static void check(){
        //TODO 链接数据库
        //TODO 检查数据表名
        //TODO 检查字段
        //TODO 检查Service,Controller,Entity是否重复
    }
}
