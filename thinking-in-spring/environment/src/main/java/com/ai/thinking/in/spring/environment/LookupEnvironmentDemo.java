package com.ai.thinking.in.spring.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖查找Environment
 */
public class LookupEnvironmentDemo implements EnvironmentAware {

    private Environment environment;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(LookupEnvironmentDemo.class);

        context.refresh();
        LookupEnvironmentDemo bean = context.getBean(LookupEnvironmentDemo.class);

        Environment environment1 = context.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);

        System.out.println(environment1 == bean.environment);

        context.close();
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
