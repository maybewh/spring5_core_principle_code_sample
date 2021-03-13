package com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.declaration_service_invocation;

public class DeclarationServiceInvocationTest {

    public static void main(String[] args) {
        TransactionInterface obj = (TransactionInterface) ProxyFactory.getProxyObject(TransactionInterface.class);
        obj.getSystem();
    }
}
