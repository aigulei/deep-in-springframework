package com.ai.thinking.in.spring.bean.lifecycle;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

public class BeanMetadataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/user.properties";
        int beanNumbers = reader.loadBeanDefinitions(location);
        System.out.println("已加载 BeanDefinition 数量："+beanNumbers);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
