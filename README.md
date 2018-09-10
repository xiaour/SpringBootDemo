## 相关文章

1.[SpringMVC配置太多？试试SpringBoot](https://xiaour.github.io/2018/05/02/The_SpringMVC_configuration_is_too_much_Try_SpringBoot/)

2.[Springboot集成Kafka](https://xiaour.github.io/2018/05/23/Springboot_integrated_Kafka/)

3.[Springboot集成RocketMQ](https://xiaour.github.io/2018/08/16/SpringbootRocketMQ/)


### 代码部分

⭐️ SpringBootDemo
本代码集成了SpringBoot+MyBatis+Redis+MySql。
最新的部分经网友指正已经把冗余的代码去掉了，大家clone到本地后直接转成maven项目应该就可以运行了，项目中使用到的数据库表如下

```sql
-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `age` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'xiaour', '18');
```

⭐️ SpringBootDemoV2
代码主要和SpringBootDemo的区别是使用了Springboot2.0

2018.08.22 添加了Twitter ID生成算法的工具，每秒支持12万ID生成。

⭐️ SpringBootKafkaDemo
Springboot2.0继承了Kafka消息中间件

⭐️ SpringBootRocketMqDemo
Springboot2.0继承了RocketMQ4.3消息中间件


---------------------------------
有兴趣的朋友可以关注一下最新开源的
[spring.boot.sapi.starter](https://github.com/xiaour/spring.boot.sapi.starter)

