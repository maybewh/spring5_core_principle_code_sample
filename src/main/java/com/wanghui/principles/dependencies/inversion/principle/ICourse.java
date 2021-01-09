package com.wanghui.principles.dependencies.inversion.principle;

/**
 *  依赖倒置原则：相当于依靠开闭原则。或者说：面向接口编程
 *  场景：学习多门课程
 */
public interface ICourse {

    void study(Person person);

}
