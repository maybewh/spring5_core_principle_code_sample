package com.wanghui.design.pattern.proxy.jdk_dynamic_proxy;

public class Girl implements Person {

    @Override
    public void findLove() {
        System.out.println("高富帅");
        System.out.println("身高180cm");
        System.out.println("有6块腹肌");
    }
}
