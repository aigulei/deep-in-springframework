package com.ai.java.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/**
 * {@link java.beans.BeanInfo}
 */
public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class,Object.class);
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
                    System.out.println(propertyDescriptor);
                    //propertyDescriptor允许添加属性编辑器：PropertyEditor
                    //name->String
                    //age->Integer
                    Class<?> propertyType = propertyDescriptor.getPropertyType();
                    String propertyName = propertyDescriptor.getName();
                    if("age".equals(propertyName)){//为age字段字段增加PropertyEditor
                        propertyDescriptor.setPropertyEditorClass(String2IntegerPropertyEditor.class);
                        //propertyDescriptor.createPropertyEditor();
                    }
                });
    }
    static class String2IntegerPropertyEditor extends PropertyEditorSupport{
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }
}
