package com.cpf.frame4j.inter;

import com.cpf.frame4j.aop.ProxyChain;

/**
 * 代理接口
 */
public interface IProxy {

    /**
     * 执行链式代理
     */
    Object doProxy(ProxyChain proxyChain);

}
