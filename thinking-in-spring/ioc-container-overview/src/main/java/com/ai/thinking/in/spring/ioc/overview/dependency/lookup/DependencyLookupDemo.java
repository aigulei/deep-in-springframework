package com.ai.thinking.in.spring.ioc.overview.dependency.lookup;

import com.ai.thinking.in.spring.ioc.overview.annotation.Super;
import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.util.Map;

/**
 * 依赖查找示例
 * 1:通过名称的方式来查找
 * 2:通过类型来查找
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        //配置XML配置文件
        //启动Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
        lookupInRealTime(beanFactory);
        lookupInLazy(beanFactory);
        lookupByType(beanFactory);
        lookupCollectionByType(beanFactory);
        lookupCollectionByAnnotationType(beanFactory);
    }
    private static void lookupCollectionByAnnotationType(BeanFactory beanFactory){
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String,User> users = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找标注Super注解集合的users:"+users);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory){
       if(beanFactory instanceof ListableBeanFactory){
           ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
           Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
           System.out.println("查找到所有的User集合对象："+users);
       }
    }
    private static void lookupByType(BeanFactory beanFactory){
        User user = beanFactory.getBean(User.class);
        System.out.println("类型查找...."+user);
    }

    private static void lookupInLazy(BeanFactory beanFactory){
        ObjectFactory<User> factory =  (ObjectFactory<User>)beanFactory.getBean("objectFactory");
        User user = factory.getObject();
        System.out.println("延迟查找...."+user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory){
        User user = (User)beanFactory.getBean("user");
        System.out.println("实时查找...."+user);
    }
}
