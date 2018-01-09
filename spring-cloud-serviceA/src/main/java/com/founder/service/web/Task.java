package com.founder.service.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * 定时任务测试
 *
 * @author gongmb
 * @create 2017-08-22 9:53
 **/
@Configuration
@EnableScheduling //启动定时任务
public class Task {

    private final Logger logger = LoggerFactory.getLogger(Task.class);

    /**
     * 分布式定时任务
     */
    //@Scheduled(cron = "0/20 * * * * ?")
    public void scheduler() {
        logger.info(new Date().toString());
    }
}
