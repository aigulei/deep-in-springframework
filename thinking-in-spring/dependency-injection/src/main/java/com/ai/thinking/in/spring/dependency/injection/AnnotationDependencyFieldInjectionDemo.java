package com.ai.thinking.in.spring.dependency.injection;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

public class AnnotationDependencyFieldInjectionDemo {

    @Autowired
    private
    //static Autowired会忽略掉静态字段
    UserHolder userHolder;

    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("classpath:META-INF/dependency-lookup-context.xml");

        applicationContext.refresh();

        AnnotationDependencyFieldInjectionDemo bean = applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);
        UserHolder userHolder = bean.userHolder;
        System.out.println(userHolder);
        UserHolder userHolder2 = bean.userHolder2;
        System.out.println(userHolder2);


        applicationContext.close();
    }

    @Bean
    public UserHolder createUserHolder(User user){
        return new UserHolder(user);
    }
}
