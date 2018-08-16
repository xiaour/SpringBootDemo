package com.xiaour.spring.boot.rocketmq.controller;

import com.xiaour.spring.boot.rocketmq.producer.Producer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @Author: Xiaour
 * @Description: 这里是为了测试
 * @Date: 2018/8/9 15:16
 */
@RestController
public class TestController {
    @Autowired
    private Producer producer;

    @RequestMapping("/push")
    public String pushMsg(String msg){
        try {
            return producer.send("PushTopic","push",msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }
}
