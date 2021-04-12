package com.ai.thinking.in.spring.annoation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@MyComponentScan
public @interface MyComponentScan2 {
    @AliasFor(annotation = MyComponentScan.class,attribute = "scanBasePackages")
    String[] basePackages() default {};

    String[] scanBasePackages() default {};

    @AliasFor("scanBasePackages")
    String[] packages() default {};
}
