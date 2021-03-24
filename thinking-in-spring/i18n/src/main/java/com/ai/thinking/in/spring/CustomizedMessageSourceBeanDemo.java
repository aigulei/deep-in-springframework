package com.ai.thinking.in.spring;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

@EnableAutoConfiguration
public class CustomizedMessageSourceBeanDemo {
    /**
     * 在SpringBoot场景中,Primary Configuration Source高于*AutoConfiguration
     * @return
     */
    @Bean
    public MessageSource messageSource(){
        return new ResourceBundleMessageSource();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                //Primary Configuration Class
                new SpringApplicationBuilder(CustomizedMessageSourceBeanDemo.class)
                    .web(WebApplicationType.NONE).run(args);
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        if(beanFactory.containsBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)){
            //查找MessageSource的BeanDefinition
            System.out.println(beanFactory.getBeanDefinition(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME));
            MessageSource messageSource =
                    applicationContext.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
            System.out.println(messageSource);
        }
         applicationContext.close();
    }
}
