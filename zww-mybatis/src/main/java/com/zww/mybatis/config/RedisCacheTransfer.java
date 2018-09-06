package com.zww.mybatis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zww.mybatis.utils.MybatisRedisCache;
import com.zww.mybatis.utils.RedisService;

@Component
public class RedisCacheTransfer {

    @Autowired
    public void setJedisConnectionFactory(RedisService redisService) {
        MybatisRedisCache.setRedisService(redisService);
    }
}