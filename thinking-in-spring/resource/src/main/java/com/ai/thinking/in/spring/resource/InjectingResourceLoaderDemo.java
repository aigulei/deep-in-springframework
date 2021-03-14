package com.ai.thinking.in.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

public class InjectingResourceLoaderDemo  implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Autowired
    private ResourceLoader autowiredResourceLoader;

    @Autowired
    private AbstractApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        System.out.println("resourceLoader==autowiredResourceLoader: "+(resourceLoader==autowiredResourceLoader));
        System.out.println("applicationContext==autowiredResourceLoader: "+(applicationContext==autowiredResourceLoader));
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(InjectingResourceLoaderDemo.class);
        ctx.refresh();

        ctx.close();
    }


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
