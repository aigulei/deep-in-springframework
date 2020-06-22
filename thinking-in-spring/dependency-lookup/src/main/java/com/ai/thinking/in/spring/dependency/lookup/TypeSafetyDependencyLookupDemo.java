package com.ai.thinking.in.spring.dependency.lookup;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全 依赖查找示例
 */
public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类TypeSafetyDependencyLookupDemo作为配置类
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        //演示BeanFactory#getBean方法的安全性
        displayBeanFactoryGetBean(applicationContext);
        //演示ObjectFactory#getBean方法的安全性
        displayObjectFactoryGetObject(applicationContext);
        //演示ObjectProvider#getIfAvailable方法的安全性
        displayObjectProviderGetObject(applicationContext);
        //演示ListableBeanFactory#getBeansOfType方法的安全性
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        //演示ObjectProvider的stream的安全性
        displayObjectProviderStreamOps(applicationContext);
        //关闭应用上下文
        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps",()->{
            userObjectProvider.forEach(u->{});
        });
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
        printBeansException("displayListableBeanFactoryGetBeansOfType",()->{
            beanFactory.getBeansOfType(String.class);
        });
    }

    private static void displayObjectProviderGetObject(AnnotationConfigApplicationContext applicationContext) {
        printBeansException("displayObjectProviderGetObject",()->{
            ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
            User ifAvailable = objectProvider.getIfAvailable();
        });
    }

    public static void displayObjectFactoryGetObject(BeanFactory beanFactory){
        printBeansException("displayObjectFactoryGetObject",()->{
            ObjectFactory<User> userObjectFactory = beanFactory.getBeanProvider(User.class);
            userObjectFactory.getObject();
        });
    }
    public static void displayBeanFactoryGetBean(BeanFactory beanFactory){
        printBeansException("displayBeanFactoryGetBean", ()->{
            beanFactory.getBean(User.class);
        });
    }
    private static void printBeansException(String source,Runnable runnable){
        System.err.println("============================================");
        System.err.println("Source from:"+source);
        try{
            runnable.run();
        }catch (BeansException exception){
            exception.printStackTrace();
        }
    }
}
