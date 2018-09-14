package com.cpf.frame4j.aop;

import com.cpf.frame4j.annotation.Aspect;
import com.cpf.frame4j.annotation.FController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

@Aspect(FController.class)
public class ControllerAspect extends AspectProxy {

    public static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    private long time;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        LOGGER.debug("---- begin");
        LOGGER.debug(String.format("class : %s", cls.getName()));
        LOGGER.debug(String.format("method : %s", method.getName()));
        time = System.currentTimeMillis();
    }


    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) {
        LOGGER.debug(String.format("time : %dms", System.currentTimeMillis() - time));
        LOGGER.debug("---- end");
    }
}
