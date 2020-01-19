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
        //BeanFactory applicationContext = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");

        //依赖来源一：自定义Bean
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);
        //System.out.println(userRepository.getUsers());
        //依赖来源二：依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory());


        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject()==applicationContext);

        //依赖查找(错误)
        //System.out.println(beanFactory.getBean(BeanFactory.class));

        // 依赖来源三：容器内建Bean(不是业务方或自己的应用来构建的，而是内部自己创建的)
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("获取Enviroment类型的Bean："+environment);
        //whoIsIOCContainer(userRepository,applicationContext);
    }
    private static void whoIsIOCContainer(UserRepository userRepository,ApplicationContext applicationContext){
        //这个表达式为什么不会成立
        System.out.println(userRepository.getBeanFactory()==applicationContext);
        //ApplicationContext is BeanFactory
    }
}
