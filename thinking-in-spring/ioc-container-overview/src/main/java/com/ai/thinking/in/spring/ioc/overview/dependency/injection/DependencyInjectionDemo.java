package com.ai.thinking.in.spring.ioc.overview.dependency.injection;

import com.ai.thinking.in.spring.ioc.overview.annotation.Super;
import com.ai.thinking.in.spring.ioc.overview.domain.User;
import com.ai.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * 依赖注入示例
 * 1:通过名称的方式来查找
 * 2:通过类型来查找
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
        //依赖来源一：自定义Bean
        UserRepository userRepository = beanFactory.getBean("userRepository",UserRepository.class);
        System.out.println(beanFactory);
        //System.out.println(userRepository.getUsers());
        //依赖来源二：依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory());
        System.out.println(beanFactory==userRepository.getBeanFactory());
        //依赖查找(错误代码)
        //System.out.println(beanFactory.getBean(BeanFactory.class));
        //依赖来源三：容器内建Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);
    }
    private static void whoIsIoCContainer(UserRepository userRepository,BeanFactory beanFactory){
        //ConfigurableApplicationContext<-ApplicationContxt<-BeanFactory
        //ConfigurableApplicationContext#getBeanFactory()
        //这个表达式为什么不会成立
        System.out.println(userRepository.getBeanFactory()==beanFactory);
        //ApplicationContext is BeanFactory
    }
}
