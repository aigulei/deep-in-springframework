package com.ai.thinking.in.spring.data.bingind;

import com.ai.thinking.in.spring.ioc.overview.domain.Company;
import com.ai.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

public class DataBinderDemo {
    public static void main(String[] args) {
        //创建空白对象
        User user = new User();
        //创建DataBinder
        DataBinder dataBinder = new DataBinder(user,"user");
        //创建PropertyValues
        Map<String,Object> source = new HashMap<>();
        source.put("id","1");
        source.put("name","China");
        //PropertyValues存在User中不存在的属性值
        //特性一：忽略未知的属性
        source.put("age",18);
        //特性二：支持嵌套属性
        source.put("company",new Company());
        source.put("company.name","Asiainfo");
        MutablePropertyValues mpvs = new MutablePropertyValues(source);
        //1.调整IgnoreUnknownFields true(默认）->false
        //dataBinder.setIgnoreUnknownFields(false);
        //2.调整ignoreInvalidFields false(默认) ->true(默认情况下不变化)
        //需要调整
        dataBinder.setAutoGrowNestedPaths(false);
        dataBinder.setIgnoreInvalidFields(true);
        dataBinder.setRequiredFields("id","name","city");
        dataBinder.bind(mpvs);
        //输出User内容
        System.out.println(user);

        BindingResult result = dataBinder.getBindingResult();
        System.out.println(result);

    }
}
