package com.study.page.service.impl;

import com.study.page.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public Boolean mergeKeyValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key,value);
        return true;
    }

    @Override
    public Boolean delKeyValue(String key) {
        return stringRedisTemplate.delete(key);
    }

    @Override
    public String queryValueByKey(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
