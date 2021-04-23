package com.ai.thinking.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentPropertySourceChangeDemo {

    @Value("${user.name}")
    private String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnvironmentPropertySourceChangeDemo.class);

        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String,Object> source = new HashMap<>();
        source.put("user.name","China");
        MapPropertySource propertySource = new MapPropertySource("first-property-source",source);
        propertySources.addFirst(propertySource);
        context.refresh();
        source.put("user.name","中国");
        EnvironmentPropertySourceChangeDemo environmentPropertySourceChangeDemo = context.getBean(EnvironmentPropertySourceChangeDemo.class);
        System.out.println(environmentPropertySourceChangeDemo.userName);
        for(PropertySource ps :propertySources){
            System.out.printf("PropertySource(name=%s),'user.name' 属性：%s \n",ps.getName(),ps.getProperty("user.name"));
        }
        context.close();

    }
}
