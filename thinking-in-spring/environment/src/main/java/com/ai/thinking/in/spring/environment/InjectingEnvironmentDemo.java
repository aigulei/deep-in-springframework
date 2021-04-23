package com.ai.thinking.in.spring.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 注入Environment
 */
public class InjectingEnvironmentDemo implements EnvironmentAware, ApplicationContextAware {

    private Environment environment;

    private ApplicationContext applicationContext;

    @Autowired
    private Environment environment1;

    @Autowired
    private ApplicationContext applicationContext1;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingEnvironmentDemo.class);
        context.refresh();
        InjectingEnvironmentDemo injectingEnvironmentDemo = context.getBean(InjectingEnvironmentDemo.class);
        System.out.println(injectingEnvironmentDemo.environment);
        System.out.println(injectingEnvironmentDemo.environment1);
        System.out.println(injectingEnvironmentDemo.environment == injectingEnvironmentDemo.environment1);
        System.out.println(injectingEnvironmentDemo.environment1 == context.getEnvironment());
        System.out.println("-----------------");
        System.out.println(injectingEnvironmentDemo.applicationContext);
        System.out.println(injectingEnvironmentDemo.applicationContext1);
        System.out.println(injectingEnvironmentDemo.applicationContext == injectingEnvironmentDemo.applicationContext1);
        System.out.println(context == injectingEnvironmentDemo.applicationContext);
        context.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
