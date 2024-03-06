package com.xiaour.spring.boot.service;

import com.github.xiaour.easyexport.annotation.EasyExport;
import com.github.xiaour.easyexport.annotation.EasyExportSingle;
import com.xiaour.spring.boot.entity.Cat;
import com.xiaour.spring.boot.entity.PageIndex;
import com.xiaour.spring.boot.entity.PageInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@EasyExport
@Service
public class CatService {

    /**
     * 这里主要是模拟数据和分页参数的一些主要逻辑
     * @param pageIndex 模拟的分页参数，这里面是伪代码，无需实现
     * @return
     */
    @EasyExportSingle(value = "小猫明细",modelClass = Cat.class)
    public PageInfo getCatList(PageIndex pageIndex){

        PageInfo page = new PageInfo();

        List<Cat> list = new ArrayList<>();

        //下面提供了两种实体赋值方式，大家可以试试那种更方便
        Cat cat1 = new Cat("咪咪",1,"波斯猫");

        Cat cat2 = new Cat().setNickName("汤姆").setAge(6).setCategory("金渐层");

        list.add(cat1);

        list.add(cat2);

        page.setList(list);

        page.setTotal(list.size());

        return page;
    }
}
