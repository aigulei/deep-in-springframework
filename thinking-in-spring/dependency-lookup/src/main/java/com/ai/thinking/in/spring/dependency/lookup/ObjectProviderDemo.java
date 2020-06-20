package com.ai.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link org.springframework.beans.factory.ObjectProvider}依赖查找
 */
public class ObjectProviderDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类
        applicationContext.register(ObjectProviderDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        lookUpByObjectProvider(applicationContext);
        //关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public String helloWorld(){//方法名就是Bean名称="helloWorld"
        return "hello,world";
    }
    private static void lookUpByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }

}
