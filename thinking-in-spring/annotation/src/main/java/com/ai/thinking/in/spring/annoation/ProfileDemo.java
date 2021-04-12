package com.ai.thinking.in.spring.annoation;

import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class ProfileDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ProfileDemo.class);
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.setDefaultProfiles("even");
        //environment.setActiveProfiles("odd");
        context.refresh();

        Integer number = context.getBean("number",Integer.class);
        System.out.println(number);

        context.close();
    }

    @Bean(value ="number")
    @Profile("odd")
    public Integer odd(){
        return 1;
    }

    @Bean(value = "number")
    //@Profile("even")
    @Conditional(EvenProfileCondition.class)
    public Integer even(){
        return 2;
    }

}
