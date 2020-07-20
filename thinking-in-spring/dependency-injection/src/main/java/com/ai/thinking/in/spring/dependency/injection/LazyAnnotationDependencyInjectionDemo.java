package com.ai.thinking.in.spring.dependency.injection;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LazyAnnotationDependencyInjectionDemo {
    @Autowired
    private User user;

    @Autowired
    private ObjectProvider<User> userObjectProvider;//延迟注入

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("classpath:META-INF/dependency-lookup-context.xml");

        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo bean = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);
        System.out.println("bean.user="+bean.user);
        System.out.println("bean.userObjectProvider="+bean.userObjectProvider.getObject());
        System.out.println("-----------------");
        bean.userObjectProvider.forEach(System.out::println);
        applicationContext.close();
    }
}
