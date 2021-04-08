package com.ai.thinking.in.spring;


import org.springframework.core.GenericCollectionTypeResolver;
import org.springframework.jdbc.core.metadata.GenericCallMetaDataProvider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link org.springframework.core.GenericCollectionTypeResolver}
 */
public class GenericCollectionTypeResolveDemo {

    private static StringList stringList;
    private static List<String> strings;

    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println(GenericCollectionTypeResolver.getCollectionType(StringList.class));
        System.out.println(GenericCollectionTypeResolver.getCollectionType(ArrayList.class));

        Field field = GenericCollectionTypeResolveDemo.class.getDeclaredField("stringList");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));

        field = GenericCollectionTypeResolveDemo.class.getDeclaredField("strings");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));
    }
}
