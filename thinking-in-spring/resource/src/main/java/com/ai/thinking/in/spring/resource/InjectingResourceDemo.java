package com.ai.thinking.in.spring.resource;

import com.ai.thinking.in.spring.resource.util.ResourceUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class InjectingResourceDemo {
    @Value("classpath:/META-INF/default.properties")
    private Resource defaultPropertiesResource;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] propertiesResources;

    @Value("${user.dir}")
    private String currentProjectRootPath;
    @PostConstruct
    public void init(){
        System.out.println(ResourceUtils.getContent(defaultPropertiesResource));
        System.out.println("---------------");
        Stream.of(propertiesResources).map(ResourceUtils::getContent).forEach(System.out::println);
        System.out.println("---------------");
        System.out.println("---------------");
        System.out.println(currentProjectRootPath);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(InjectingResourceDemo.class);

        ctx.refresh();
        ctx.close();
    }
}
