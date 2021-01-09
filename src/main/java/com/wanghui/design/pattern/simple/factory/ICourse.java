package com.wanghui.design.pattern.simple.factory;

/**
 *  简单的工厂模式。例如，传入一个字符串，根据字符串判断，去生成所需要的对象。
 *  但随着一个接口的实现类越来越多，需要不停地修改工厂类的if-else语句。因此，可以使用发射技术来进行统一。
 */
public interface ICourse {

    /**
     * 录制视频
     */
    void record();

}
