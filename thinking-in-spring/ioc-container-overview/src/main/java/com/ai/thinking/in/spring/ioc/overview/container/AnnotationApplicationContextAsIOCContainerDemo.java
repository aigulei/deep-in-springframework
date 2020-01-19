package com.ai.thinking.in.spring.ioc.overview.container;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * {@link org.springframework.context.ApplicationContext}
 */
@Configuration
public class AnnotationApplicationContextAsIOCContainerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类AnnotationApplicationContextAsIOCContainerDemo作为配置类(Configuration Class)
        applicationContext.register(AnnotationApplicationContextAsIOCContainerDemo.class);
        applicationContext.refresh();
        //依赖查找集合对象
        lookupCollectionByType(applicationContext);
        applicationContext.close();
    }

    @Bean
    public User user(){
        User user = new User();
        user.setId(1L);
        user.setName("AI");
        return user;
    }
    private static void lookupCollectionByType(BeanFactory beanFactory){
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到所有的User集合:"+users);
        }
    }
}
