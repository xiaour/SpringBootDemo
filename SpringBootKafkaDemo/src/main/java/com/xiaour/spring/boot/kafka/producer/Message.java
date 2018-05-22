package com.xiaour.spring.boot.kafka.producer;

import java.util.Date;

/**
 * @Author: Xiaour
 * @Description:
 * @Date: 2018/5/22 15:09
 */
public class Message {

    private String id;

    private String msg;

    private Date sendTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
