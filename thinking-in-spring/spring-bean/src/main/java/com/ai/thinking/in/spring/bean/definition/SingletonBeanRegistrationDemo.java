package com.ai.thinking.in.spring.bean.definition;

import com.ai.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.ai.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.naming.Context;
import java.beans.beancontext.BeanContext;

/**
 * 单体Bean注册实例
 */
public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) {
        //注册BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //创建一个外部对象
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        //注册外部单例对象
        beanFactory.registerSingleton("userFactory",userFactory);
        //启动Spring应用上下文
        applicationContext.refresh();

        //通过依赖查找的方式获取userFactory
        UserFactory userFactoryByLookup = beanFactory.getBean("userFactory", UserFactory.class);
        System.out.println("userFactory==userFactoryByLookup="+(userFactory==userFactoryByLookup));
        //关闭Spring应用上下文
        applicationContext.close();
    }
}
