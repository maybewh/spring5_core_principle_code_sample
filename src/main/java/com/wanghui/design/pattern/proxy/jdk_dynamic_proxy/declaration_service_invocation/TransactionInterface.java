package com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.declaration_service_invocation;

@Transaction(value = "www.baidu.com")
public interface TransactionInterface {

    @Action(value = "windows")
    public void getSystem();

}
