package com.ai.thinking.in.spring.validation;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.Locale;


/**
 * @see LocalValidatorFactoryBean
 */
public class SpringBeanValidationDemo {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-validation-context.xml");
        Validator validator = applicationContext.getBean(Validator.class);

        System.out.println(validator instanceof LocalValidatorFactoryBean);

        UserProcessor userProcessor = applicationContext.getBean(UserProcessor.class);
        User user = new User();
        user.setName("123132");
        userProcessor.process(user);
        applicationContext.close();
    }
    @Bean
    static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.name", Locale.getDefault(),"name属性不能为空");
        return messageSource;
    }
    static class MyStaticMessageSource extends  StaticMessageSource{

    }
    @Component
    @Validated
    static class UserProcessor{
        public void process(@Valid User user){
            System.out.println(user);
        }
    }

    static class User{
        @NotNull(message = "{user.name}")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


}
