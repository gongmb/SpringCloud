package com.founder.service.web;

import com.founder.service.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.founder.service.bo.User;

@EnableAutoConfiguration
@RestController
public class ComputeController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;
    
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public String add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return "From Service-A, Result is " + r;
    }

    
    @RequestMapping(value = "/test1" ,method = RequestMethod.GET)
    public String test1(@RequestParam Integer a, @RequestParam Integer b) {
    	User user=new User("zhangsan", "111111");
    	user=userService.add(user);
    	return "From Service-A, Result is " + user.toString();
    }
    
    @Value("${profile}")
    private String profile;

    @RequestMapping(value = "/test2" ,method = RequestMethod.GET)
    public String test2() {
    	return this.profile+":"+this.profile;
    }
    
}