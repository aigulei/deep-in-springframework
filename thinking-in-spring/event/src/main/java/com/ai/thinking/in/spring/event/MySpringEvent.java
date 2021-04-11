package com.ai.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;

public class MySpringEvent extends ApplicationEvent {

    public MySpringEvent(String  message) {
        super(message);
    }

    @Override
    public String getSource() {
        return (String)super.getSource();
    }

    public String getMessage(){
        return getSource();
    }
}
