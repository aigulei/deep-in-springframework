package com.ai.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * {@link UserFactory}默认实现
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {
    //1基于@PostConstruct注解
    @PostConstruct
    public void init(){
        System.out.println("@PostConstruct:UserFactory初始化中....");
    }

    public void initUserFactory(){
        System.out.println("自定义初始化方法initUserFactory():UserFactory初始化中....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet():UserFactory初始化中....");
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("@PreDestroy:UserFactory销毁中....");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy():UserFactory销毁中....");
    }

    public void destroyFactory(){
        System.out.println("自定义销毁UserFactory");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("当前DefaultUserFactory正在被回收。。。");
    }
}
