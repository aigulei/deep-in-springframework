package com.ai.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * {@link UserFactory}默认实现
 */
public class DefaultUserFactory implements UserFactory, InitializingBean {
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
}
