package com.wanghui.design.pattern.simple.factory;

/**
 * 通过传入指定的字符串来生成对应的对象
 */
public class SimpleFactoryByClassName {

    public ICourse create(String name) {

        if ("java".equals(name)) {
            return new JavaCourse();
        } else if ("python".equals(name)) {
            return new PythonCourse();
        } else {
            return null;
        }
    }
}
