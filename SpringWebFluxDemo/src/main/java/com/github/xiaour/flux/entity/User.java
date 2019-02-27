package com.github.xiaour.flux.entity;

/**
 * @Author: cityuu@suiyueyule.com
 * @Date: 2019-02-27 16:31
 * @version: v1.0
 * @Description:
 */
public class User {

    private String id;

    private String name;

    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
