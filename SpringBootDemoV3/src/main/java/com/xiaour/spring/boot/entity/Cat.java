package com.xiaour.spring.boot.entity;

import com.alibaba.excel.annotation.ExcelProperty;

public class Cat {

    /**
     * 宠物昵称
     */
    @ExcelProperty(value = "昵称")
    private String nickName;

    /**
     * 年龄
     */
    @ExcelProperty(value = "年龄")
    private Integer age;

    /**
     * 品种
     */
    @ExcelProperty(value = "品种")
    private String category;

    public Cat() {
    }

    public Cat(String nickName, Integer age, String category) {
        this.nickName = nickName;
        this.age = age;
        this.category = category;
    }

    public String getNickName() {
        return nickName;
    }

    public Cat setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Cat setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Cat setCategory(String category) {
        this.category = category;
        return this;
    }
}
