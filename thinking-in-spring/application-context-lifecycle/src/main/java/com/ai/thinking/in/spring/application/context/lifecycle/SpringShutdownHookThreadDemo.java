package com.ai.thinking.in.spring.application.context.lifecycle;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

public class SpringShutdownHookThreadDemo {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();
        context.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                System.out.println("关闭事件。。。");
            }
        });
        context.refresh();
        context.registerShutdownHook();
        System.out.println("按任意键继续并且关闭Spring应用上下文");
        System.in.read();
        context.close();
    }
}
