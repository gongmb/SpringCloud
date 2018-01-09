package com.springcloud.study.serviceimpl;


import com.springcloud.study.model.RedisModel;
import com.springcloud.study.service.IRedisService;
import org.springframework.stereotype.Service;
/**
 * @author gongmb
 * @create 2017-08-30 11:44
 **/
@Service
public class RedisServiceImpl extends IRedisService<RedisModel> {
    private static final String REDIS_KEY = "TEST_REDIS_KEY";

    @Override
    protected String getRedisKey() {
        return this.REDIS_KEY;
    }
}