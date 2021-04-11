package com.ai.thinking.in.spring.event;

import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@link org.springframework.context.ApplicationListener}
 * @see ApplicationListener
 * @see EventListener
 */
@EnableAsync
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
       //GenericApplicationContext context = new GenericApplicationContext();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationListenerDemo.class);
        //方法一:向Spring应用上下文注册事件
        //基于ConfigurableApplicatonContext的API实现
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                println("ApplicationListener接收到Spring事件:"+event);
            }
        });
        //基于ApplicationListener注册Spring Bean

        //方法二：基于Spring注解@EventListener
        context.register(MyApplicationListener.class);
        //启动Spring应用上下文
        context.refresh();
        context.start();
        //关闭Spring应用上下文
        context.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("hello world") {
        });
        applicationEventPublisher.publishEvent("hello world");
        applicationEventPublisher.publishEvent(new MyPayloadApplicationEvent(this,"Hello,World"));
    }
    static class MyPayloadApplicationEvent<String> extends PayloadApplicationEvent<String>{

        /**
         * Create a new PayloadApplicationEvent.
         *
         * @param source  the object on which the event initially occurred (never {@code null})
         * @param payload the payload object (never {@code null})
         */
        public MyPayloadApplicationEvent(Object source, String payload) {
            super(source, payload);
        }
    }

    /* @EventListener
     public void onApplicationEvent(ContextRefreshedEvent event){
         System.out.println("@EventListener接收到消息：");
     }*/
   static class MyApplicationListener implements ApplicationListener<ContextStartedEvent>{

       @Override
       public void onApplicationEvent(ContextStartedEvent event) {
           println("MyApplicationListener 接收到消息："+event);
       }
   }
    @EventListener
    @Order(3)
    public void onApplicationEvent(ContextStartedEvent event){
        println("@EventListener接收到消息：");
    }
    @EventListener
    @Order(2)
    public void onApplicationEvent1(ContextStartedEvent event){
        println("@EventListener接收到消息1：");
    }
    @EventListener
    @Async
    public void onApplicationEventAsync(ContextStartedEvent event){
        println("@EventListener异步接收到消息：");
    }
    @EventListener
    public void onApplicationEvent(ContextClosedEvent event){
        println("@EventListener接收到消息：");
    }
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event){
        println("@EventListener接收到消息：");
    }
    private static void println(Object printable){
        System.out.printf("[线程：%s]:%s\n",Thread.currentThread().getName(),printable);
    }
}
