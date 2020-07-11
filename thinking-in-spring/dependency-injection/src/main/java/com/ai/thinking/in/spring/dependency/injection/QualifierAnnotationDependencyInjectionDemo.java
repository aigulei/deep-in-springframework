package com.ai.thinking.in.spring.dependency.injection;

import com.ai.thinking.in.spring.dependency.injection.annotation.UserGroup;
import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

public class QualifierAnnotationDependencyInjectionDemo {
    @Autowired
    private User user;
    @Autowired
    @Qualifier("user")
    private User nameUser;
    //整体应用上下文存在4个User类型的Bean
    //superUser
    //user
    //user1
    //user2

    @Autowired
    private Collection<User> allUsers;//2个Beans user+superUser

    @Autowired
    @Qualifier
    private Collection<User> qualfiedUsers;//2个Beans user1+user2

    @Autowired
    @UserGroup
    private Collection<User> groupUsers;
    @Bean
    @Qualifier
    public User user1(){
        return createUser(7L);
    }
    @Bean
    @Qualifier
    public User user2(){
        return createUser(8L);
    }
    @Bean
    @UserGroup
    public User user3(){
        return createUser(9L);
    }
    @Bean
    @UserGroup
    public User user4(){
        return createUser(10L);
    }
    private static User createUser(Long id){
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions("classpath:META-INF/dependency-lookup-context.xml");
        applicationContext.refresh();
        QualifierAnnotationDependencyInjectionDemo bean = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        System.out.println("demo.user"+bean.user);
        System.out.println("demo.namedUser"+bean.nameUser);
        System.out.println("demo.allUsers="+bean.allUsers);
        System.out.println("demo.qualifiedUsers="+bean.qualfiedUsers);
        System.out.println("bean.groupUsers="+bean.groupUsers);
        applicationContext.close();
    }
}
