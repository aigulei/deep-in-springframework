package com.ai.thinking.in.spring.validation;

import com.ai.thinking.in.spring.ioc.overview.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.stream.Stream;

public class JavaBeansDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class,Object.class);
        //属性描述符
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(
                propertyDescriptor ->{
                    System.out.println(propertyDescriptor);
                }
        );
        Stream.of(beanInfo.getMethodDescriptors()).forEach(
                System.out::println
        );

    }
}
