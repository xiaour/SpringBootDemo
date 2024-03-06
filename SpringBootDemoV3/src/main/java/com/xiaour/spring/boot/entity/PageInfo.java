package com.xiaour.spring.boot.entity;

import java.util.List;

public class PageInfo {

    private List<?> list;

    private Integer total;

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
