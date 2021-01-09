package com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.gpproxy;

import com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.Girl;
import com.wanghui.design.pattern.proxy.jdk_dynamic_proxy.Person;

public class GPProxyTest {

    public static void main(String[] args) {
        try {

            //JDK动态代理的实现原理
            Person obj = (Person) new GPMeipo().getInstance(new Girl());
            System.out.println(obj.getClass());
            obj.findLove();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
