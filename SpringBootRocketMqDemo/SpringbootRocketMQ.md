---
title: Springboot集成ROcketMQ
categories: [Springboot],[MQ]
date: 2018-05-23 10:43:12
tags:
---

<img src="http://rocketmq.apache.org/assets/images/bright/rmq-feature-lowlatency.png" alt=post-cover"">

有一段时间没有写东西了，最近工作和生活上发生了一些变化，总算是有点时间来歇一歇自己想要分享的，2018年转眼过去一多半了，想来自己今年好像也没有太多的积累。尽可能的让自己能够多做一些技术上的事情。

从开篇写Springboot系列的教程已经一年多了，Springboot也从1.x升级到了2.x，目前教程中的代码部分大多都是使用Springboot2.x了。

Springboot从开始到流行起来的到现在的风靡是很多开发越来越简单，经我调查发现大部分开发人员都已经开始在生产环境使用，除了一些老的项目或者难以改变技术架构的项目外，Springboot可以说是作为JAVA开发人员必不可少的伙伴了。

如今大多数的JAVA系组件都开发除了自己的boot版本，有一些做的比较优秀，也有一些做的不是那么好，本篇要介绍的RocketMQ在boot上做的就不算很好，有很多开发者自己封装了boot版本，官方可能会在未来出现更好版本的boot；但是没有boot版没关系，咱们自己几行配置也是完全可以做到快速使用RocketMQ了。

在这里我要提一下官方版本的RocketMQ4.3，因为本文就是基于RocketMQ4.3的，"RocketMQ4.3正式发布支持了分布式事务"。这一消息让很多开发者跃跃欲试，以前rocketMQ没有将分布式事务作为一个发行版的部分，基本都是开发者自己实现的事务部分。下面我们从最基础的安装RocketMQ开始，再介绍代码中如何实现基本的配置。

###下载安装RocketMQ

```
# wget http://mirrors.hust.edu.cn/apache/rocketmq/4.3.0/rocketmq-all-4.3.0-source-release.zip
# unzip rocketmq-all-4.3.0-source-release.zip
# mvn -Prelease-all -DskipTests clean install -U
# cd distribution/target/apache-rocketmq
```

###启动NameServer，启动后NameServer的端口是9876，请确保自己的9876端口未被占用
```
# nohup sh bin/mqnamesrv &
# tail -f ~/logs/rocketmqlogs/namesrv.log
  The Name Server boot success...
```

### 启动Broker
```
# nohup sh bin/mqbroker -n localhost:9876 &
# tail -f ~/logs/rocketmqlogs/broker.log 
  The broker[%s, 172.30.30.233:10911] boot success...
```
启动成功了之后我们就可以创建新的Springboot项目了，如何创建项目这里我就不在介绍了，Eclipse和Idea的方式大同小异，目录结构基本都是一样的。

首先每次说到Springboot的项目都是要先讲讲这个boot的配置，按照惯例呢我先给配置，配置上都有每一行配置的注释，大家可以参考。
### 代码示例pom.xml
```
    <parent>
    		<groupId>org.springframework.boot</groupId>
    		<artifactId>spring-boot-starter-parent</artifactId>
    		<version>2.0.4.RELEASE</version>
    		<relativePath/>
    	</parent>
    
    	<properties>
    		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    		<java.version>1.8</java.version>
    	</properties>
    
    	<dependencies>
    		<dependency>
    			<groupId>org.springframework.boot</groupId>
    			<artifactId>spring-boot-starter-data-redis</artifactId>
    		</dependency>
    
    		<dependency>
    			<groupId>org.springframework.boot</groupId>
    			<artifactId>spring-boot-starter-test</artifactId>
    			<scope>test</scope>
    		</dependency>
    
            <!--RocketMQ的dependency，目前官方还没有boot-starter-->
    		<dependency>
    			<groupId>org.apache.rocketmq</groupId>
    			<artifactId>rocketmq-client</artifactId>
    			<version>4.3.0</version>
    		</dependency>
    		
    		<dependency>
    			<groupId>org.springframework.boot</groupId>
    			<artifactId>spring-boot-starter-web</artifactId>
    			<version>RELEASE</version>
    		</dependency>
    	</dependencies>
```
###application.yml
```
apache:
  rocketmq:
#消费者的配置
    consumer:
      pushConsumer: XiaourPushConsumer
#生产者的配置
    producer:
      producerGroup: Xiaour
#Nameserver的地址,这里配置你MQ安装的机器上的IP就好，我这里在本机安装的
    namesrvAddr: 127.0.0.1:9876
 ```
 
###Producer 消息生产者
```java
import org.apache.commons.lang3.time.StopWatch;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

/**
 * @Author: Xiaour
 * @Description:生产者
 * @Date: 2018/8/9 14:52
 */

@Component
public class Producer {

    /**
     * 生产者的组名
     */
    @Value("${apache.rocketmq.producer.producerGroup}")
    private String producerGroup;

    private DefaultMQProducer producer;
    /**
     * NameServer 地址
     */
    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @PostConstruct
    public void defaultMQProducer() {

        //生产者的组名
        producer= new DefaultMQProducer(producerGroup);
        //指定NameServer地址，多个地址以 ; 隔开
        producer.setNamesrvAddr(namesrvAddr);
        producer.setVipChannelEnabled(false);
        try {
            producer.start();
            System.out.println("-------->:producer启动了");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public String send(String topic,String tags,String body) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        Message message = new Message(topic, tags, body.getBytes(RemotingHelper.DEFAULT_CHARSET));
        StopWatch stop = new StopWatch();
        stop.start();
        SendResult result = producer.send(message);
        System.out.println("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
        stop.stop();
        return "{\"MsgId\":\""+result.getMsgId()+"\"}";
    }
}
```
###Consumer 消息消费者

```java
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

/**
 * @Author: Xiaour
 * @Description:消费者
 * @Date: 2018/8/9 14:51
 */

@Component
public class Consumer implements CommandLineRunner {

    /**
     * 消费者
     */
    @Value("${apache.rocketmq.consumer.pushConsumer}")
    private String pushConsumer;

    /**
     * NameServer 地址
     */
    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;


    /**
     * 初始化RocketMq的监听信息，渠道信息
     */
    public void messageListener(){

        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("SpringBootRocketMqGroup");

        consumer.setNamesrvAddr(namesrvAddr);
        try {

            // 订阅PushTopic下Tag为push的消息,都订阅消息
            consumer.subscribe("PushTopic", "push");

            // 程序第一次启动从消息队列头获取数据
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            //可以修改每次消费消息的数量，默认设置是每次消费一条
            consumer.setConsumeMessageBatchMaxSize(1);

            //在此监听中消费信息，并返回消费的状态信息
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {

                // 会把不同的消息分别放置到不同的队列中
                for(Message msg:msgs){

                    System.out.println("接收到了消息："+new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });

            consumer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        this.messageListener();
    }
}
```
###测试接口用例
这里我们用一个接口来测试我们的消息发送会不会被消费者接收。

```java
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
```
在Springboot启动类启动后,在浏览器访问http://127.0.0.1:8080/push?hello，页面提示{"MsgId":"AC100AB660C618B4AAC2XXXXXXXX"}就表示消息发送成功啦。
我们可以再IDE控制台中看到输出的结果，
```text
发送响应：MsgId:AC100AB660C618B4AAC2XXXXXXXX，发送状态:SEND_OK
接收到了消息：hello
```
这时候我们的整合基本上就完成啦。 

具体代码可以在github获取哦。SpringBootRocketMqDemo@github](https://github.com/xiaour/SpringBootDemo/tree/master/SpringBootRocketMqDemo)获取哦。