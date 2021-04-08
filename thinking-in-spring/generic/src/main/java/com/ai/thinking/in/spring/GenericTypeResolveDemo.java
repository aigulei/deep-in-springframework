package com.ai.thinking.in.spring;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@link }
 */
public class GenericTypeResolveDemo {
    public static void main(String[] args) throws NoSuchMethodException {
        displayReturnTypeGenericInfo(GenericTypeResolveDemo.class,List.class,"getString");
        displayReturnTypeGenericInfo(GenericTypeResolveDemo.class,List.class,"getList");
        displayReturnTypeGenericInfo(GenericTypeResolveDemo.class,List.class,"getStringList");
        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
    }
    public static StringList getStringList(){
        return null;
    }
    public static <E> List<E> getList(){
        return null;
    }
    public static String getString(){
        return null;
    }
    private static void displayReturnTypeGenericInfo(Class<?> containingClass,Class<?> genericClass,String methodName,Class... argumentTypes) throws NoSuchMethodException {
        Method method = containingClass.getMethod(methodName, argumentTypes);
        Class<?> returnType = GenericTypeResolver.resolveReturnType(method, containingClass);
        System.out.printf("GenericTypeResolver.resolveReturnType(%s,%s)=%s\n",methodName,containingClass.getSimpleName(),returnType);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s,%s)=%s\n",methodName,containingClass.getSimpleName()
                ,GenericTypeResolver.resolveReturnTypeArgument(method,genericClass));
    }

}
