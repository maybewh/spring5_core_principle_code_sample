package com.wanghui.principles.dependencies.inversion.principle;

public class Tom {

    private ICourse course;

    public Tom(ICourse course) {
        this.course = course;
    }

    public void study() {
        Person person = new Person();
        person.setName("Tom");
        course.study(person);
    }

}
