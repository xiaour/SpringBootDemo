package com.github.xiaour.flux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author: cityuu@163.com
 * @Date: 2019-02-27 16:26
 * @version: v1.0
 * @Description: 入门案例
 */

@RestController
@RequestMapping("/")
public class HelloWorlController {

        @GetMapping("hello")
        public Mono<String> hello(){
            return Mono.just("hello World!");
        }

}
