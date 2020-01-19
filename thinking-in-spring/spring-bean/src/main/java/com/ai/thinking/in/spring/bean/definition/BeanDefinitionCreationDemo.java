package com.ai.thinking.in.spring.bean.definition;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link  org.springframework.beans.factory.config.BeanDefinition}
 */
public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        //1.通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //通过属性设置
        beanDefinitionBuilder.addPropertyValue("id",1);
        beanDefinitionBuilder.addPropertyValue("name","AI");
        //获取BeanDefinition实例
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //BeanDefinition并非Bean终态，可以自定义修改

        //2.通过AbstractBeanDefinition以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        //设置Bean类型
        genericBeanDefinition.setBeanClass(User.class);
        //通过MutableProperties批量操作属性
        MutablePropertyValues propertyValues = new MutablePropertyValues();
//        propertyValues.addPropertyValue("id",1);
//        propertyValues.addPropertyValue("name","AI");
        propertyValues.add("id",1).add("name","AI");
        //通过setMutablePropertyValues批量操作属性
        genericBeanDefinition.setPropertyValues(propertyValues);

    }
}
