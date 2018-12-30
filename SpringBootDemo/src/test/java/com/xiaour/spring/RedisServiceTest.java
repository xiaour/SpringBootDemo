package com.xiaour.spring;

import com.xiaour.spring.boot.Application;
import com.xiaour.spring.boot.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {Application.class},
        initializers = {ConfigFileApplicationContextInitializer.class})
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void test() {
        String username = redisService.get("username");
        System.out.println(username);
    }
}
