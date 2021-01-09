package com.wanghui.design.pattern.abstract_factory;

public class JavaFactory implements ICourseFactory{

    @Override
    public INote createNote() {
        return new JavaNote();
    }

    @Override
    public IVideo createVideo() {
        return new JavaVideo();
    }

}
