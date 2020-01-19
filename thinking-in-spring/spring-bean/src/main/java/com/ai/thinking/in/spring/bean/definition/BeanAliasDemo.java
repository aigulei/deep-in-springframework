package com.ai.thinking.in.spring.bean.definition;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAliasDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("/META-INF/bean-definitions-context.xml");
        User aiUer = beanFactory.getBean("ai-user",User.class);
        User user = beanFactory.getBean("user",User.class);
        System.out.println("aiUser是否与userBean相同:"+(user==aiUer));
    }
}
