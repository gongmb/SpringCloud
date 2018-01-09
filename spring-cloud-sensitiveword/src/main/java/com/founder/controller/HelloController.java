package com.founder.controller;

import com.founder.utils.FileUtils;
import com.founder.utils.SensitivewordFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RESTful API test
 *
 * @author gongmb
 * @create 2017-08-24 11:22
 **/
@RestController
public class HelloController {

    private static final Logger _log = LoggerFactory
            .getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String index(){
        ServiceInstance serviceInstance=discoveryClient.getLocalServiceInstance();
        _log.info("/hello,host:"+serviceInstance.getHost()+",serviceId:"+serviceInstance.getServiceId());
        return "RESTful API test";
    }


    public static String SensitiveWordFilePath;
    @Value("${SensitiveWordFilePath}")
    public void setSensitiveWordFilePath(String SensitiveWordFilePath){
        this.SensitiveWordFilePath=SensitiveWordFilePath;
    }
    @RequestMapping(value = "/replaceSensitiveWord" ,method = RequestMethod.GET)
    public String add(@RequestParam String str) {
        SensitivewordFilter filter= FileUtils.getobj();
        _log.info("敏感词转换前:{}",str);
        String str1=filter.replaceSensitiveWord(str,4,"*");
        _log.info("敏感词转换前:{}",str1);
        return str1 ;
    }
}
