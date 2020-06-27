package com.ai.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link NoSuchBeanDefinitionException}
 */
public class NoSuchBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前NoSuchBeanDefinitionExceptionDemo设置为配置类
        applicationContext.register(NoSuchBeanDefinitionExceptionDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        try{
            applicationContext.getBean(String.class);
        }catch (NoUniqueBeanDefinitionException e){
            System.err.printf("Spring应用上下文存在%d个%s类型的Bean，具体原因%s\n",
                    e.getNumberOfBeansFound(),String.class.getName(),e.getMessage());
        }

        //关闭应用上下文
        applicationContext.close();
    }
    @Bean
    public String bean1(){
        return "bean1";
    }
    @Bean
    public String bean2(){
        return "bean2";
    }
}
