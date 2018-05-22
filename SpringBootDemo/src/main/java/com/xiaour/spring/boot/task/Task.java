package com.xiaour.spring.boot.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling // 启用定时任务
public class Task {
	
    @Scheduled(cron="0 0/1 * * * ?")
	public void run(){
		System.out.println("Scheduled Running...");
	}
}
