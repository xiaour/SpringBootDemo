package com.github.xiaour.controller;

import com.github.xiaour.config.DuckPorperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Xiaour
 * @Description:
 * @Date: 2018/3/1 下午4:54
 */
@RestController
public class DemoController {
    @Autowired
    private DuckPorperties duckPorperties;

    @RequestMapping("/amazing")
    public Map<String,Object> amazing() {
        /**
         * 如果不需要其他的结构，可以直接返回对象类型，不必要强制JSON格式。Springboot默认支持转为json
         */
        Map<String,Object> obj= new HashMap<>();
        obj.put("name","xiaour");
        obj.put("age","18");
        obj.put("age","18");
        return obj;
    }

    @RequestMapping("/duck")
    public String duck() {
        /**
         * 自动装配模式的配置文件属性。
         */
        return duckPorperties.getDuckName();
    }




}
