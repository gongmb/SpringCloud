/*
package com.springcloud.study;

import com.springcloud.study.controller.HelloController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import  static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

*/
/**
 * springboot 单元测试
 *
 * @author gongmb
 * @create 2017-08-24 11:29
 **//*

@RunWith(SpringJUnit4ClassRunner.class) //引入对Junit4的支持
//该注解SpringApplicationConfiguration 在1.4就被SpringBootTest替换了
//@org.springframework.boot.test.SpringApplicationConfiguration
@SpringBootTest(classes = HelloController.class) //指定SpringBoot启动类
@WebAppConfiguration //开启web应用的配置,用于模拟servletContext.
public class HelloApplicationTests {
    //用于模拟调用controller的接口发起请求
    //测试用例中perform函数执行一次请求调用,accept用于执行接收的数据类型,andExpect用户判断接口期望的返回值
    private MockMvc mvc;

    //Junit中定义在执行@test方法钱预加载内容,这里用于初始化HelloController
    @Before
    public void setup()throws Exception{
        mvc= MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void hello()throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("RESTful API test")));

    }
}
*/
