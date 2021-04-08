package com.ai.thinking.in.spring;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Stream;

public class GenericAPIDemo {
    public static void main(String[] args) {
        Class intClass = int.class;

        Class objectArrayClass  = Object[].class;

        Class rawClass = String.class;

        ParameterizedType genericClass = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        System.out.println(genericClass);
        Stream.of(genericClass.getActualTypeArguments())

                .forEach(System.out::println);
    }
}
