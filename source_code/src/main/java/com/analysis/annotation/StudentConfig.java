package com.analysis.annotation;

import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;

public class StudentConfig implements ImportAware {


    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {

    }
}
