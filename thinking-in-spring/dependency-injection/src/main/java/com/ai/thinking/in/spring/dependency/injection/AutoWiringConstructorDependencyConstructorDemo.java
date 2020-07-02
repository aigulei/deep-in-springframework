package com.ai.thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class AutoWiringConstructorDependencyConstructorDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory application = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(application);
        beanDefinitionReader.loadBeanDefinitions("classpath:META-INF/autowiring-dependency-constructor-injection.xml");
        UserHolder userHolder = application.getBean(UserHolder.class);
        System.out.println(userHolder.getUser());
    }
}
