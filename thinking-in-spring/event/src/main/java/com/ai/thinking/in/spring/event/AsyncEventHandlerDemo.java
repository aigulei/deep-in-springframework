package com.ai.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步时间处理器示例
 */
public class AsyncEventHandlerDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.addApplicationListener(new MySpringEventListener());
        context.refresh();

        ApplicationEventMulticaster applicationEventMulticaster =
                context.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
        if(applicationEventMulticaster instanceof SimpleApplicationEventMulticaster){
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster
                    = (SimpleApplicationEventMulticaster)applicationEventMulticaster;
            ExecutorService executor = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool"));
            simpleApplicationEventMulticaster.setTaskExecutor(executor);
            applicationEventMulticaster.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
                @Override
                public void onApplicationEvent(ContextClosedEvent event) {
                    System.out.println("线程关闭...");
                    if(!executor.isShutdown()){
                        executor.shutdown();
                    }

                }
            });
            applicationEventMulticaster.addApplicationListener(new ApplicationListener<MySpringEvent>() {
                @Override
                public void onApplicationEvent(MySpringEvent event) {
                    throw  new RuntimeException("abcdefg....");
                }
            });
            simpleApplicationEventMulticaster.setErrorHandler( t ->{
                System.out.println("当Spring事件异常时，原因："+t.getMessage());
            });
        }

        context.publishEvent(new MySpringEvent("Hello world"));

        context.close();
    }
}
