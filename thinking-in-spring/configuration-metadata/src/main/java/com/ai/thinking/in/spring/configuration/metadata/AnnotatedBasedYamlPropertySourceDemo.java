package com.ai.thinking.in.spring.configuration.metadata;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import com.ai.thinking.in.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * {@link org.springframework.beans.factory.config.YamlMapFactoryBean}
 */
@PropertySource(name = "yamlPropertySource"
        ,value = "classpath:META-INF/user.yaml"
        ,factory = YamlPropertySourceFactoryDemo.class)
public class AnnotatedBasedYamlPropertySourceDemo {

    @Bean
    public User user(@Value("${user.id}")Long id,@Value("${user.name}") String name,@Value("${user.city}") City city){
        User user = new User();
        user.setId(id);
        user.setName("China");
        user.setCity(city);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AnnotatedBasedYamlPropertySourceDemo.class);
        ctx.refresh();

        User user = ctx.getBean("user", User.class);
        System.out.println(user);

        ctx.close();
    }

}
