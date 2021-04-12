package com.ai.thinking.in.spring.annoation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@ComponentScan
public @interface MyComponentScan {
    @AliasFor(annotation = ComponentScan.class,attribute = "value")
    String[] scanBasePackages() default {};

}
