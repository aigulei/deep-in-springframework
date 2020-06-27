package com.ai.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);

        applicationContext.registerBeanDefinition("pojo",beanDefinitionBuilder.getBeanDefinition());
        //启用应用上下文
        applicationContext.refresh();
        //关闭应用上下文
        applicationContext.close();
    }

    static class POJO implements InitializingBean {

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("for puropose.....");
        }
    }
}
