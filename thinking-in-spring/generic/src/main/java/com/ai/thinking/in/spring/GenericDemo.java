package com.ai.thinking.in.spring;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

public class GenericDemo {
    public static void main(String[] args) {
        Collection<String> list  = new ArrayDeque<>();
        list.add("hello");
        list.add("world");
        Collection temp = list;
        temp.add(1);
        System.out.println(list);
    }
}
