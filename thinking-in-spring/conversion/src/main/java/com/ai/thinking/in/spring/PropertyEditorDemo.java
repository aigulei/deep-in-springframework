package com.ai.thinking.in.spring;

import java.beans.PropertyEditor;

public class PropertyEditorDemo {
    public static void main(String[] args) {
        String text = "name = China";
        PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        propertyEditor.setAsText(text);
        System.out.println(propertyEditor.getValue());
        System.out.println(propertyEditor.getAsText());
    }
}
