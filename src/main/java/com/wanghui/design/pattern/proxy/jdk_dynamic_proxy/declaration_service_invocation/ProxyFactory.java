package com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.declaration_service_invocation;

import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static Object getProxyObject(Class<?>... clazz) {
        return Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), clazz, new TransactionHandler());
    }
}
