package com.github.xiaour.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.xiaour.service.DubboService;

/**
 * @Author: Xiaour
 * @Description:
 * @Date: 2018/4/25 16:59
 */


@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DubboServiceImpl implements DubboService {
    @Override
    public String hello(String name) {
        return "Hello,"+name;
    }
}
