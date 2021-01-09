package com.wanghui.design.pattern.simple.factory;

/**
 * 通过传入类名，然后通过反射来实例化对象。
 * 该方法的问题：传入的是字符串，可控性不强，并且生成对象后，需要进行强制转化。所以还需要优化
 */
public class SimpleFactoryByReflectionStringName {

    public ICourse create(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        if (className != null && !"".equals(className.trim())) {

            return (ICourse) Class.forName(className).newInstance();

        }

        return null;

    }
}
