package com.ai.thinking.in.spring.dependency.lookup;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

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
        lookUpIfAvailable(applicationContext);
        lookUpByStreamOps(applicationContext);
        //关闭应用上下文
        applicationContext.close();
    }

    private static void lookUpByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        Iterable<String> stringIterable = beanProvider;
        for(String s:stringIterable){
            System.out.println(s);
        }
        System.out.println("-----------");
        beanProvider.stream().forEach(System.out::println);
    }

    private static void lookUpIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        System.out.println(beanProvider.getIfAvailable(()-> User.createUser()));
    }

    @Bean
    @Primary
    public String helloWorld(){//方法名就是Bean名称="helloWorld"
        return "hello,world";
    }
    @Bean
    public String message(){
        return "Message";
    }
    private static void lookUpByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }

}
