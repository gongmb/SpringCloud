package com.founder.service;

import com.founder.service.conf.MyBatisConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //开启事务支持
@Import({MyBatisConfig.class})//启动时加载mybatis配置
@EnableAutoConfiguration
@EnableDiscoveryClient //发现服务配置 -- config-server
@SpringBootApplication
@EnableEurekaClient //注册中心
public class ServiceApplication {

	
	public static void main(String[] args) {
		new SpringApplicationBuilder(ServiceApplication.class).web(true).run(args);
	}

}
