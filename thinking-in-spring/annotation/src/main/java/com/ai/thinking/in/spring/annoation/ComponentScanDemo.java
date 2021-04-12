package com.ai.thinking.in.spring.annoation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@MyComponentScan2(basePackages="com.ai.thinking.in.spring.annoation")
//@MyComponentScan(scanBasePackages="com.ai.thinking.in.spring.annoation")
//@ComponentScan(basePackages = "com.ai.thinking.in.spring.annoation")
public class ComponentScanDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ComponentScanDemo.class);
        context.refresh();
        TestClass bean = context.getBean(TestClass.class);
        System.out.println(bean);
        context.close();
    }
}
