package com.ai.thinking.in.spring.annoation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@MyComponentScan2(packages = "com.ai.thinking.in.spring.annoation")
public class AttributeOverridesDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AttributeOverridesDemo.class);

        context.refresh();
        TestClass bean = context.getBean(TestClass.class);
        System.out.println(bean);
        context.close();
    }
}
