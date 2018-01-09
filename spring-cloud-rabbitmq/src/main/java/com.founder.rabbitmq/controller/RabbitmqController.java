package com.founder.rabbitmq.controller;

import com.founder.rabbitmq.producer.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * rabbitmq测试类
 *
 * @author gongmb
 * @create 2017-08-22 10:36
 **/
@RestController
@RequestMapping("/rabbit")
public class RabbitmqController {

    @Autowired
    private Sender helloSender1;
    @Autowired
    private Sender helloSender2;

    @PostMapping("/hello")
    public void hello() {
        helloSender1.send();
    }
}
