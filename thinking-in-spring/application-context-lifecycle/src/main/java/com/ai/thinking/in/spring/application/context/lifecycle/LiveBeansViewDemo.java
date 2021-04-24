package com.ai.thinking.in.spring.application.context.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

import static org.springframework.context.support.LiveBeansView.MBEAN_DOMAIN_PROPERTY_NAME;

public class LiveBeansViewDemo {
    public static void main(String[] args) throws IOException {
        System.setProperty(MBEAN_DOMAIN_PROPERTY_NAME,"com.ai.thinking.in.spring");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(LiveBeansViewDemo.class);
        context.refresh();

        System.out.println("按任意键继续");
        System.in.read();
        context.close();
    }
}
