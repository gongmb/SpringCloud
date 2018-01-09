package com.springcloud.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon消费者测试
 *
 * @author gongmb
 * @create 2017-08-29 14:44
 **/
@EnableDiscoveryClient//注册为euraka客户端应用,以及服务发现能力
@SpringBootApplication
public class ConsumerApplication {

    /**
     * 创建RestTemplate的springbean实例
     * @return
     */
    @Bean
    @LoadBalanced//注解开启客户端负载均衡
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
