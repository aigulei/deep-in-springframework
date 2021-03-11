package com.ai.thinking.in.spring.configuration.metadata;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;
import java.util.Set;

@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/user-bean-config.properties")
@PropertySource("classpath:/META-INF/user-bean-config.properties")
public class AnnotatedSpringIoCContainerMetadataConfiguration {
    @Bean
    public User configuredUser(@Value("${user.id}") Long id,@Value("${user.name}")String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AnnotatedSpringIoCContainerMetadataConfiguration.class);
        ctx.refresh();
        Map<String, User> usersMap = ctx.getBeansOfType(User.class);
        for(Map.Entry<String, User> user: usersMap.entrySet()){
            System.out.printf("User Bean name:  %s,content:%s\n",user.getKey(),user.getValue());
        }
        ctx.close();

    }
}
