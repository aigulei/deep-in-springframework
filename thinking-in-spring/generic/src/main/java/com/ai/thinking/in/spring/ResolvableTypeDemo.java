package com.ai.thinking.in.spring;

import org.springframework.core.ResolvableType;

public class ResolvableTypeDemo {
    public static void main(String[] args) {
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);
        System.out.println(resolvableType.getSuperType());
        System.out.println(resolvableType.getSuperType().getSuperType());
        System.out.println(resolvableType.asCollection().resolve());
    }
}
