package com.founder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者controller
 *
 * @author gongmb
 * @create 2017-08-29 14:51
 **/
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String helloConsumer(@RequestParam String str) {
        return restTemplate.getForEntity("http://SPRINGCLOUDSENSITIVEWORD/add?str="+str, String.class).getBody();
    }
}
