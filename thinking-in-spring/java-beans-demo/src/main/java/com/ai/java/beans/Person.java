package com.ai.java.beans;

/**
 * 描述人的POJO类
 * Setter/Getter方法
 * 可写方法(Writable)/可读方法(Readable)
 */
public class Person {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
