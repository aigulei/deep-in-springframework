package com.ai.thinking.in.spring.configuration.metadata;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class ExtensibleXmlAuthoringDemo {
    public static void main(String[] args) {
        //创建IOC底层容器
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        //创建XML资源的BeanDefinition
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        //加载XML资源
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:META-INF/user-context.xml");
        User bean = defaultListableBeanFactory.getBean(User.class);
        System.out.println(bean);
    }
}
