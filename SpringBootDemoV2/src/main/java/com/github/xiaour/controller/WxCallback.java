package com.github.xiaour.controller;

import com.github.xiaour.utils.Xml2JsonUtil;
import com.google.gson.JsonObject;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 消息与事件接收
 * @Author: Xiaour
 * @Description:
 * @Date: 2018/3/12 下午5:25
 */

@RestController
public class WxCallback {

    @RequestMapping("callback/auth")
    public String callbackAuth(String auth_code,Integer expires_in){
        System.out.println("auth_code:"+auth_code);
        System.out.println("expires_in:"+expires_in);
        return "success";
    }


    @RequestMapping("callback/{appid}")
    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String callbackMsg(@RequestBody String requestBody, @PathVariable String appid){
        System.out.println(appid);
        System.out.println(requestBody);
        try {
            JsonObject jsonData= Xml2JsonUtil.xml2Json(requestBody);
            System.out.println(jsonData);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "success";
    }


}
