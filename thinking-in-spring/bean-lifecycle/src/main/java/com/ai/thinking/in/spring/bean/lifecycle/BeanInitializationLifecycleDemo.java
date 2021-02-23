package com.ai.thinking.in.spring.bean.lifecycle;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean初始化生命周期
 */
public class BeanInitializationLifecycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
    }

    private static void executeBeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //方法一：添加BeanPostProcessor实现MyInstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //添加CommonAnnotationBeanPostProcessor解决@PostConstruct
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
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
