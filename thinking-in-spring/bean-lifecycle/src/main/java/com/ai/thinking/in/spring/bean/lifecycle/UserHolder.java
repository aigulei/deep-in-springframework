package com.ai.thinking.in.spring.bean.lifecycle;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class UserHolder implements BeanNameAware, BeanClassLoaderAware,BeanFactoryAware , EnvironmentAware,
        InitializingBean,SmartInitializingSingleton,DisposableBean {
    private final User user;
    private Integer num;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public UserHolder(User user){
        this.user = user;
    }

    /**
     * 依赖于注解驱动
     * 当前场景：BeanFactory
     */
    @PostConstruct
    public void initPostConstruct(){
        this.description = "the user holder v4";
        System.out.println("initPostConstruct =  "+this.description);
    }
    @PreDestroy
    public void preDestroy(){
        this.description = "the user holder v10";
        System.out.println("preDestroy()="+description);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        this.description = "the user holder v5";
        System.out.println("afterPropertiesSet = "+this.description);

    }
    public void init(){
        this.description = "the user holder v6";
        System.out.println("init = "+this.description);
    }
    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", num=" + num +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private String beanName;
    private Environment environment;
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    @Override
    public void afterSingletonsInstantiated() {
        this.description = "the user holder v8";
        System.out.println("afterSingletonsInstantiated = "+this.description);
    }

    @Override
    public void destroy() throws Exception {
        this.description = "the user holder v11";
        System.out.println("destroy()="+description);
    }

    public void doDestroy(){
        this.description = "the user holder v12";
        System.out.println("doDestroy()="+description);
    }
}
