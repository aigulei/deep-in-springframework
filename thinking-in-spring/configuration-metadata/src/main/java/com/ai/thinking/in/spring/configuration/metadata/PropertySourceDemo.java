package com.ai.thinking.in.spring.configuration.metadata;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

@PropertySource("classpath:/META-INF/user-bean-config.properties")
public class PropertySourceDemo {

    @Bean
    public User user(@Value("${user.id}") Long id,@Value("${user.name}")String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        Map<String, Object> source = new HashMap<>();
        source.put("user.name","China");
        org.springframework.core.env.PropertySource propertySource = new MapPropertySource("first-prop",source);
        ctx.getEnvironment().getPropertySources().addFirst(propertySource);
        ctx.register(PropertySourceDemo.class);
        ctx.refresh();

        Map<String, User> usersMap = ctx.getBeansOfType(User.class);
        for(Map.Entry<String,User> userEntry:usersMap.entrySet()){
            System.out.println(userEntry.getKey()+"...."+userEntry.getValue());
        }
        System.out.println( ctx.getEnvironment().getPropertySources());
        ctx.close();
    }
}
