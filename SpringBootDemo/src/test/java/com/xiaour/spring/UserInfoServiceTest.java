package com.xiaour.spring;

import com.alibaba.fastjson.JSON;
import com.xiaour.spring.boot.Application;
import com.xiaour.spring.boot.entity.UserInfo;
import com.xiaour.spring.boot.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.Context;

/**
 * test elasticsearch
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {Application.class},
        initializers = {ConfigFileApplicationContextInitializer.class})
public class UserInfoServiceTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void test() {
        UserInfo userInfo = userInfoService.selectByPrimaryKey(1);
        System.out.println(JSON.toJSONString(userInfo));
    }
}
