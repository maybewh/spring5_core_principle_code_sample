package com.wanghui.design.pattern.abstract_factory;

public class PythonFactory implements ICourseFactory {
    @Override
    public INote createNote() {
        return new PythonNote();
    }

    @Override
    public IVideo createVideo() {
        return new PythonVideo();
    }
}
