package com.ai.thinking.in.spring.environment;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertyPlaceHolderDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("META-INF/placeholder-resolve.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
        context.close();
    }
}
