package com.hirrao.health.service;

import com.hirrao.health.common.enums.RedisKeyEnum;
import org.springframework.stereotype.Service;

@Service
public interface RedisService {

    Object get(RedisKeyEnum key, String id);

    Boolean set(RedisKeyEnum key, String id, Object value);
}
