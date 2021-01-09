package com.wanghui.design.pattern.factory.method;

import com.wanghui.design.pattern.simple.factory.ICourse;
import com.wanghui.design.pattern.simple.factory.PythonCourse;

public class PythonCourseFactory implements ICourseFactory {

    @Override
    public ICourse create() {
        return new PythonCourse();
    }


}
