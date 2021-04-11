package com.ai.thinking.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步时间处理器示例
 */
@EnableAsync
public class AnnotatedAsyncEventHandlerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotatedAsyncEventHandlerDemo.class);

        context.refresh();

        context.publishEvent(new MySpringEvent("Hello world"));

        context.close();
    }

    @Async //同步->切换成异步
    @EventListener
    public void onEvent(MySpringEvent event){
        System.out.printf("[线程 :%s ] onEvent 监听到事件 :%s\n",Thread.currentThread().getName(),event);
    }
    @Bean
    public ExecutorService taskExecutor(){
        ExecutorService executor = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool"));
        return executor;
    }
}
