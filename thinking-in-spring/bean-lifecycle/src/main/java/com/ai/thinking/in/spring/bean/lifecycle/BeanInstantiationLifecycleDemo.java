package com.ai.thinking.in.spring.bean.lifecycle;

import com.ai.thinking.in.spring.ioc.overview.annotation.Super;
import com.ai.thinking.in.spring.ioc.overview.domain.SuperUser;
import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * Bean实例化生命周期
 */
public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
        System.out.println("----------------");
        executeApplication();
    }
    private  static void executeApplication(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        String[] locations = {"META-INF/dependency-lookup-context.xml","META-INF/bean-constructor-dependency-injection.xml"};
        applicationContext.setConfigLocations(locations);
        applicationContext.refresh();

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        User superUser = applicationContext.getBean("superUser",User.class);
        System.out.println(superUser);

        //构造器注入按照类型注入，resolveDependency
        UserHolder userHolder = applicationContext.getBean("userHolder",UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();

    }
    private static void executeBeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //方法一：添加BeanPostProcessor实现MyInstantiationAwareBeanPostProcessor
        //beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //方法二：将MyInstantiationAwareBeanPostProcessor作为Bean注册
        //基于XML资源BeanDefinitionReader实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml","META-INF/bean-constructor-dependency-injection.xml"};
/*        Resource resource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");*/
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载BeanDefinition数量:"+beanNumbers);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser",User.class);
        System.out.println(superUser);

        //构造器注入按照类型注入，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder",UserHolder.class);
        System.out.println(userHolder);
    }
}
