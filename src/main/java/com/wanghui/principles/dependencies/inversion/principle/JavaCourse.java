package com.wanghui.principles.dependencies.inversion.principle;

public class JavaCourse implements ICourse {

    @Override
    public void study(Person person) {

        System.out.println(person.getName() + "正在学习多门课程");

    }
}
