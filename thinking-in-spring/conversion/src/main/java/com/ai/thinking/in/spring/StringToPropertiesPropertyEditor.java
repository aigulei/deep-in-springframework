package com.ai.thinking.in.spring;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class StringToPropertiesPropertyEditor extends PropertyEditorSupport implements PropertyEditor{

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setValue(properties);
    }

    @Override
    public String getAsText() {
        Properties properties = (Properties) getValue();
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        StringBuilder stringBuilder = new StringBuilder();
        for(Map.Entry<Object, Object> entry:properties.entrySet()){
            stringBuilder.append(entry.getKey()).append("=").append(entry.getValue());
        };
        return stringBuilder.toString();
    }
}
