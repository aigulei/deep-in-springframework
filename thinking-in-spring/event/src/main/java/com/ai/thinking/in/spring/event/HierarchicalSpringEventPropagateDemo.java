package com.ai.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.HashSet;
import java.util.Set;

public class HierarchicalSpringEventPropagateDemo {
    public static void main(String[] args) {
        //1.创建Parent Spring应用上下文
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        parentContext.register(MyListener.class);
        //2.创建current Spring应用上下文
        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");
        currentContext.register(MyListener.class);
        //3.current ->parent
        currentContext.setParent(parentContext);
        parentContext.refresh();
        currentContext.refresh();
        currentContext.close();
        parentContext.close();
    }
    static class MyListener implements ApplicationListener<ApplicationContextEvent> {
        private static Set<ApplicationContextEvent> processedEvents = new HashSet<>();
        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            if(processedEvents.add(event)){
                System.out.printf("监听到Spring应用上下文[ID：%s ]的 %s\n",event.getApplicationContext().getId(),event.getClass().getSimpleName());
            }
        }
    }
}
