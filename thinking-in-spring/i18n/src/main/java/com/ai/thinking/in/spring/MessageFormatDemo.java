package com.ai.thinking.in.spring;

import org.springframework.cglib.core.Local;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @see java.text.MessageFormat
 */
public class MessageFormatDemo {
    public static void main(String[] args) {
        int planet = 7;
        String event = "a disturbance in the Force";
        String messagePattern = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        MessageFormat messageFormat = new MessageFormat(messagePattern);
        String result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);
        //重置MessageFormatPattern
        messagePattern = "This is a text:{0}";
        messageFormat.applyPattern(messagePattern);
        result = messageFormat.format(new Object[]{"Hello World"});
        System.out.println(result);
        //重置Locale
        messageFormat.setLocale(Locale.ENGLISH);
        messagePattern = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        messageFormat.applyPattern(messagePattern);
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        //重置Format
        //根据参数索引来设置Pattern
        messageFormat.setFormat(1,new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"));
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);
    }
}
