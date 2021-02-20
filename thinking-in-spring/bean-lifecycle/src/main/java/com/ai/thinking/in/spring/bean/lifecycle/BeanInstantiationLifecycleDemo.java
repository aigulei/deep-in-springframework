package com.ai.thinking.in.spring.bean.lifecycle;

import com.ai.thinking.in.spring.ioc.overview.annotation.Super;
import com.ai.thinking.in.spring.ioc.overview.domain.SuperUser;
import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * Bean实例化生命周期
 */
public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml","META-INF/bean-constructor-dependency-injection.xml"};
/*        Resource resource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");*/
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载BeanDefinition数量:"+beanNumbers);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser",User.class);
        System.out.println(superUser);

        //构造器注入按照类型注入，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder",UserHolder.class);
        System.out.println(userHolder);
    }
    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
                return new SuperUser();
            }
            return null;//保持SpringIOC容器实例化操作
        }
    }
}
