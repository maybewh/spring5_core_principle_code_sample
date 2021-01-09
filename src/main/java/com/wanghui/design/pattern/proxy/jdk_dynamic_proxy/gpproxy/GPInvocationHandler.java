package com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.gpproxy;

import java.lang.reflect.Method;

/**
 * 字节码重组
 */
public interface GPInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}
