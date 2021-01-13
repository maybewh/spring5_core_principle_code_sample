package com.wanghui.design.pattern.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;

public class CglibTest {

    public static void main(String[] args) throws Exception {

        /**
         * 1.JDK采取采取的是读取接口的信息
         * 2.CGlib覆盖父类的方法
         * 目的：都是生成一个新的类，去实现增强代码逻辑的功能
         *
         * JDK Proxy 对于用户而言，必须要有一个接口实现，目标类相对来说复杂
         * CGlib可以代理任意一个普通的类，没有任何要求
         *
         * CGLib生成代理逻辑更复杂，调用效率更高，生成一个包含所有逻辑的FastClass，不再需要反射调用
         * JDK Proxy生成代理逻辑简单，执行效率相对较低，每次都要反射动态调用，
         *
         * CGLib需要注意点：不能代理final修饰的方法
         */

        String path = CglibTest.class.getResource("").getPath();
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, path);

        Customer obj = (Customer) new CGlibMeipo().getInstance(Customer.class);
        System.out.println(obj.toString()); //会调用obj对象的toString方法，默认的Object的toString也是会被代理的,而在toString()方法中又会调用Object对象的hashCode()，该方法也会被代理
        obj.findLove();


    }
}
