package com.ai.thinking.in.spring.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * 自定义依赖注解，只关注User
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {
}
