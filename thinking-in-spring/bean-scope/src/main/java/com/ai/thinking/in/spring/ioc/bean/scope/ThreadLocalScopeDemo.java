package com.ai.thinking.in.spring.ioc.bean.scope;

import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class ThreadLocalScopeDemo {

    @Bean
    public User user(){
        return  createUser();
    }
    private static User createUser(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME,new ThreadLocalScope());
        });
        applicationContext.refresh();

        scopedBeansByLookup(applicationContext);
        applicationContext.close();
    }
    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext){
        for(int i =0;i<3;i++){
            Thread thread = new Thread(() ->{
                User user = applicationContext.getBean("user",User.class);
                System.out.println("user = "+user);
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext){

    }
}
