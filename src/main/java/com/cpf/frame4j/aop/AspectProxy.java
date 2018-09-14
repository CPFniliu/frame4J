package com.cpf.frame4j.aop;

import com.cpf.frame4j.inter.IProxy;
import com.cpf.frame4j.util.validate.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public abstract class AspectProxy implements IProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    public final Object doProxy(ProxyChain proxyChain) {
        Object result = null;
        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();
        try {
            if (intercept(cls, method, params)) {
                before(cls, method, params);
                result = proxyChain.doProxyChain();
                after(cls, method, params, result);
            }
        } catch (Throwable throwable) {
            LoggerUtil.loggerAndThrow(LOGGER, "proxy failure", throwable);
            error(cls, method, params, throwable);
        } finally {
            end();
        }
        return result;
    }

    public boolean intercept(Class<?> cls, Method method, Object[] params) {
        return true;
    }

    public void begin() {
    }

    public void before(Class<?> cls, Method method, Object[] params) {
    }

    public void after(Class<?> cls, Method method, Object[] params, Object result) {
    }

    public void error(Class<?> cls, Method method, Object[] params, Throwable throwable) {
    }

    public void end() {
    }


}
