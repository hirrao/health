package com.hirrao.health.service.impl;

import com.hirrao.health.common.enums.RedisKeyEnum;
import com.hirrao.health.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Object get(RedisKeyEnum key, String id) {
        return redisTemplate.opsForValue()
                            .get(key.getKey() + ':' + id);
    }

    public Boolean set(RedisKeyEnum key, String id, Object value) {
        return redisTemplate.opsForValue()
                            .setIfAbsent(key.getKey() + ':' + id, value,
                                         key.getExpireTime(), TimeUnit.MINUTES);
    }
}
