package com.wanghui.design.pattern.factory.method;

import com.wanghui.design.pattern.simple.factory.ICourse;

/**
 * 工厂方法模式：指定一个创建对象的接口，但让实现这个接口的类来决定
 * 实例化哪个类，工厂方法模式让类的实例化推迟到子类中进行。在工厂方法模式中用户只需要关注
 * 所需产品对应的工厂，无须创建细节。
 */
public interface ICourseFactory {

    ICourse create();

}
