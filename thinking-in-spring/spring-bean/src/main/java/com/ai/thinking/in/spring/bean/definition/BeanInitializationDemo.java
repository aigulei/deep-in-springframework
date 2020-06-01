package com.ai.thinking.in.spring.bean.definition;

import com.ai.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.ai.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BeanInitializationDemo {
    public static void main(String[] args) {
        //创建BeanFactory实例
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration class类
        applicationContext.register(BeanInitializationDemo.class);
        //启动Spring应用上下文
        applicationContext.refresh();
        //非延迟初始化在Spring应用上下文初始化后执行
        System.out.println("Spring应用上下文已经启动...");
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        //关闭
        applicationContext.close();
    }

    @Bean(initMethod = "initUserFactory")
    @Lazy(value = false)
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }
}
