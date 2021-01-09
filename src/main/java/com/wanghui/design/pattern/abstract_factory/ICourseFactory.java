package com.wanghui.design.pattern.abstract_factory;

/**
 * 抽象工厂：
 * 缺点：1）规定了所有可能被创建的产品集合，产品族中扩展新产品困难，需要修改抽象工厂类
 * 2）增加系统的抽象性和理解性
 */
public interface ICourseFactory {

    INote createNote();

    IVideo createVideo();

}
