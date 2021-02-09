package com.ai.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

public class DependencySourceDemo {
    //注入在PostProcessProperties方法执行，早于setter注入，也早于PostConstruct注入
    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ApplicationEventPublisher applicationEventMulticaster;
    @PostConstruct
    public void initByInjection(){
        System.out.println("(beanFactory==applicationContext)="+(beanFactory==applicationContext));
        System.out.println("（beanFactory==applicationContext）="+(beanFactory==applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("（resourceLoader==applicationContext）="+(resourceLoader==applicationContext));
        System.out.println("（ApplicationEventPublisher==applicationContext）="+(applicationEventMulticaster==applicationContext));
    }
    @PostConstruct
    public void initByLookup(){
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }
    private <T> T getBean(Class<T> beanType){
        try{
            return beanFactory.getBean(beanType);
        }catch (NoSuchBeanDefinitionException e){
            System.err.println("当前类型："+beanType.getName()+"无法在BeanFactory中查找");

        }
        return null;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);
        applicationContext.refresh();

        DependencySourceDemo demo = applicationContext.getBean(DependencySourceDemo.class);

        applicationContext.close();
    }
}
