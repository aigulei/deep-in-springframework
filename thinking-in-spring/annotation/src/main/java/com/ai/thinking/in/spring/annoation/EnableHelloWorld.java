package com.ai.thinking.in.spring.annoation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 激活HelloWorld模块注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(HelloWorldConfiguration.class)
//@Import(HelloWorldSelectConfig.class)
@Import(HelloWorldBeanDefinitionRegistrar.class)
public @interface EnableHelloWorld {
}
