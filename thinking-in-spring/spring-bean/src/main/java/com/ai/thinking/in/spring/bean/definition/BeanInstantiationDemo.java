package com.ai.thinking.in.spring.bean.definition;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean实例化示例
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        //配置XML文件配置
        //启动Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-instantiation-context.xml");
        User user = beanFactory.getBean("user-by-static-method", User.class);
        User userByInstanceMethod  = beanFactory.getBean("user-by-instance-method",User.class);
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean",User.class);
        System.out.println(user);
        System.out.println(userByInstanceMethod);
        System.out.println(userByFactoryBean);
        System.out.println(user==userByInstanceMethod);
        System.out.println(user==userByFactoryBean);
    }
}
