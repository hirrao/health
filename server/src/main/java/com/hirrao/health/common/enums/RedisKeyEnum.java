package com.hirrao.health.common.enums;

import lombok.Getter;

@Getter
public enum RedisKeyEnum {
    /**
     * 缓存健康一言, 过期时间一天
     */
    HEALTH_ADVICE("health_advice", 60 * 24L);

    private final String key;
    private final Long expireTime;

    RedisKeyEnum(String key, Long expireTime) {
        this.key = key;
        this.expireTime = expireTime;
    }
}