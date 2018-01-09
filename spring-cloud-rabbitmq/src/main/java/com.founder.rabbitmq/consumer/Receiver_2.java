package com.founder.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author gongmb
 * @create 2017-08-22 10:34
 **/
@Component
@RabbitListener(queues = "helloQueue") //监听队列
public class Receiver_2 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver2  : " + hello);
    }
}
