package com.xiaour.spring.boot.service;

import java.util.List;

/**
 * Created by zhangtao on 2017/11/8.
 */

public interface RedisService {

    boolean set(String key, String value);

    String get(String key);

    boolean expire(String key, long expire);

    <T> boolean setList(String key, List<T> list);

    <T> List<T> getList(String key, Class<T> clz);

    long lpush(String key, Object obj);

    long rpush(String key, Object obj);

    void hmset(String key, Object obj);

    <T> T hget(String key, Class<T> clz);


    void del(String key);

    <T> List<T> hmGetAll(String key, Class<T> clz);

    String lpop(String key);
}
