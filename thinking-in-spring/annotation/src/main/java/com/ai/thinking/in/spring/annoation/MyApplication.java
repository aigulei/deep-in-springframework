package com.ai.thinking.in.spring.annoation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent2
@MyConfiguration(name="my-application")
public @interface MyApplication {
}
