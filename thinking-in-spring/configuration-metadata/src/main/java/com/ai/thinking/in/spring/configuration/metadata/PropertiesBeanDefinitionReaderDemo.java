package com.ai.thinking.in.spring.configuration.metadata;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

public class PropertiesBeanDefinitionReaderDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        int beanDefinitionsCount = beanDefinitionReader.loadBeanDefinitions("META-INF/user-bean-config.properties");
        System.out.println(String.format("已加载%s个BeanDefinition",beanDefinitionsCount));

        User user = beanFactory.getBean( User.class);
        System.out.println(user);
    }
}
