package com.ai.thinking.in.spring.configuration.metadata;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.util.ObjectUtils;

public class BeanConfigurationMetadataDemo {
    public static void main(String[] args) {
        //BeanDefinition的定义(声明）
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name","hello");
        //获取AbstractBeanDefinition
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //附加属性（不影响Bean实例化、属性赋值、初始化）
        beanDefinition.setAttribute("name","hello1");
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if(ObjectUtils.nullSafeEquals("user",beanName)&&User.class.equals(bean.getClass())){
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                    if (BeanConfigurationMetadataDemo.class.equals(bd.getSource())){
                        String name =(String) bd.getAttribute("name");
                        User user = (User) bean;
                        user.setName(name);
                    }

                }
                return bean;
            }
        });
        //注册User的BeanDefinition
        beanFactory.registerBeanDefinition("user",beanDefinitionBuilder.getBeanDefinition());

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
