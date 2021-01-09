package com.wanghui.principles.dependencies.inversion.principle;

/**
 *  依赖倒置原则：依赖于抽象而不是依赖于细节。比如，Tom依赖的是对所有课程的抽象
 *  相当于依靠开闭原则。或者说：面向接口编程。
 *  场景：学习多门课程
 */
public class Test {

    public static void main(String[] args) {

        ICourse course = new AICourse();
        Tom tom = new Tom(course);
        tom.study();

    }
}
