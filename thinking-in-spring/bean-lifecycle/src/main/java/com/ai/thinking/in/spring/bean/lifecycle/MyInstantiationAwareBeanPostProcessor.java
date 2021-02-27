package com.ai.thinking.in.spring.bean.lifecycle;

import com.ai.thinking.in.spring.ioc.overview.domain.SuperUser;
import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
            return new SuperUser();
        }
        return null;//保持SpringIOC容器实例化操作
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
            User user = (User)bean;
            user.setId(2L);
            user.setName("helloAI");
            return false;
        }
        return true;
    }

    //user是跳过Bean属性赋值（填入）
    //superUser也是完全跳过Bean实例化（Bean属性赋值）
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if(ObjectUtils.nullSafeEquals("userHolder",beanName)&&UserHolder.class.equals(bean.getClass())){
            final MutablePropertyValues propertyValues;
            if(pvs instanceof  MutablePropertyValues){
                propertyValues = (MutablePropertyValues)pvs;
            }else{
                propertyValues = new MutablePropertyValues();
            }
            propertyValues.addPropertyValue("num","1");
            if(propertyValues.contains("description")){
                //PropertyValue不可变
                //PropertyValue description = propertyValues.getPropertyValue("description");
                propertyValues.removePropertyValue("description");
                propertyValues.addPropertyValue("description","the userHolder v2");
            }
            return propertyValues;
                /*UserHolder userHolder = (UserHolder) bean;
                userHolder.setNum(2);*/
        }
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(ObjectUtils.nullSafeEquals("userHolder",beanName)&&UserHolder.class.equals(bean.getClass())){
            UserHolder userHolder = (UserHolder) bean;
            userHolder.setDescription("the userHolder v3");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(ObjectUtils.nullSafeEquals("userHolder",beanName)&&UserHolder.class.equals(bean.getClass())){
            UserHolder userHolder = (UserHolder) bean;
            userHolder.setDescription("the userHolder v7");
        }
        return bean;
    }
}
