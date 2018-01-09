package com.springcloud.study.controller;

import org.apache.catalina.connector.Request;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * RESTful API test
 *
 * @author gongmb
 * @create 2017-08-24 11:22
 **/
@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String index(){
        ServiceInstance serviceInstance=discoveryClient.getLocalServiceInstance();
        logger.info("/hello,host:"+serviceInstance.getHost()+",serviceId:"+serviceInstance.getServiceId());
        return "RESTful API test";
    }
}
