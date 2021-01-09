package com.wanghui.design.pattern.simple.factory;

public class JavaCourse implements ICourse {

    @Override
    public void record() {
        System.out.println("正在录制Java课程");
    }
}
