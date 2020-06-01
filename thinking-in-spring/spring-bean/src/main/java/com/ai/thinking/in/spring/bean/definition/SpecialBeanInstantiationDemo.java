package com.ai.thinking.in.spring.bean.definition;

import com.ai.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.ai.thinking.in.spring.bean.factory.UserFactory;
import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Bean实例化示例
 */
public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/special-bean-instantiation-context.xml");
        ServiceLoader serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(serviceLoader);

        //demoServiceLoader();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/special-bean-instantiation-context.xml");
        AutowireCapableBeanFactory beanFactory1 = applicationContext.getAutowireCapableBeanFactory();
        UserFactory userFactory = beanFactory1.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());

    }
    public static void demoServiceLoader(){
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }
    private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader){
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }
}
