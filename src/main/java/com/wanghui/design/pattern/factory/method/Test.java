package com.wanghui.design.pattern.factory.method;

import com.wanghui.design.pattern.simple.factory.ICourse;

/**
 * 工厂方法模式适用场景：
 * 1）创建对象需要大量重复的代码
 * 2）客户端（应用层）不依赖于产品类实例如何被创建、如何被实现的细节
 * 3）一个类通过其子类创建对象
 * 缺点：
 * 1) 类的个数容易过多
 * 2）增加了系统的抽象性和理解难度
 */
public class Test {

    public static void main(String[] args) {
        ICourseFactory courseFactory = new JavaCourseFactory();
        ICourse course = courseFactory.create();
        course.record();
    }
}
