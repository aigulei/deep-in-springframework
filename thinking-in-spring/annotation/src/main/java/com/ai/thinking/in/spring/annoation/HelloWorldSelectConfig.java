package com.ai.thinking.in.spring.annoation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class HelloWorldSelectConfig implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.ai.thinking.in.spring.annoation.HelloWorldConfiguration"};
    }
}
