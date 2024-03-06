package com.xiaour.spring.boot.controller;

import com.xiaour.spring.boot.entity.PageIndex;
import com.xiaour.spring.boot.entity.PageInfo;
import com.xiaour.spring.boot.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cat")
public class CatController {

    private CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("list")
    public PageInfo getCatList() {
        PageIndex index = new PageIndex();
        index.setPageIndex(1);
        index.setPageSize(30);
        return catService.getCatList(index);
    }





}
