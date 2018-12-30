package com.xiaour.spring.boot.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {
    /**  */
    private Integer id;

    /**  */
    private String name;

    /**  */
    private Integer age;

    private static final long serialVersionUID = 1L;

    /**  */
    public Integer getId() {
        return id;
    }

    /**  */
    public void setId(Integer id) {
        this.id = id;
    }

    /**  */
    public String getName() {
        return name;
    }

    /**  */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**  */
    public Integer getAge() {
        return age;
    }

    /**  */
    public void setAge(Integer age) {
        this.age = age;
    }
}