package com.ai.thinking.in.spring.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * {@link java.util.Observer}
 */
public class ObserverDemo {
    public static void main(String[] args) {
        Observable observable = new EventObservable();
        Observer observer = new EventObserver();
        observable.addObserver(observer);
        //发布事件
        observable.notifyObservers("hello world");
    }
    static class EventObservable extends  Observable{
        @Override
        protected void setChanged() {
            super.setChanged();
        }

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }
    }
    static class EventObserver implements Observer, EventListener {

        @Override
        public void update(Observable o, Object message) {
            System.out.println("收到事件："+message);
        }
    }
}
