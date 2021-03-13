package com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.declaration_service_invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 声明式服务调用
 */
public class TransactionHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Transaction requestUrl = method.getDeclaringClass().getAnnotation(Transaction.class);
        System.out.println("通过Method获取该方法所对应的对象：" + requestUrl.value());

/*        Transaction transaction = proxy.getClass().getAnnotation(Transaction.class);
        String requestUrl = transaction.value(); */ // 说明继承该接口的代理对象无法继承其声明的注释

        Action action = method.getAnnotation(Action.class);
        String requestParam = action.value();
        System.out.println(requestUrl.value() + "?" + requestParam);
        return null;
    }

}
