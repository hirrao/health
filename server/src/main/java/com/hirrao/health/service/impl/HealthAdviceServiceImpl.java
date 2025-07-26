package com.hirrao.health.service.impl;

import com.hirrao.health.common.converter.HealthAdviceConverter;
import com.hirrao.health.common.enums.RedisKeyEnum;
import com.hirrao.health.common.exception.ClientException;
import com.hirrao.health.common.response.HealthAdviceArticleListResponse;
import com.hirrao.health.common.response.HealthAdviceArticleResponse;
import com.hirrao.health.dao.HealthAdviceDao;
import com.hirrao.health.dao.UserDao;
import com.hirrao.health.entity.HealthAdvice;
import com.hirrao.health.service.HealthAdviceService;
import com.hirrao.health.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HealthAdviceServiceImpl implements HealthAdviceService {
    private final HealthAdviceDao healthAdviceDao;
    private final UserDao userDao;
    private final HealthAdviceConverter converter;
    private final RedisService redisService;

    @Autowired
    public HealthAdviceServiceImpl(HealthAdviceDao healthAdviceDao,
                                   UserDao userDao,
                                   HealthAdviceConverter converter,
                                   RedisService redisService) {
        this.healthAdviceDao = healthAdviceDao;
        this.userDao = userDao;
        this.converter = converter;
        this.redisService = redisService;
    }

    @Override
    public HealthAdviceArticleResponse getById(Long id) {
        //检查是否已经缓存于Redis
        var cachedAdvice = redisService.get(RedisKeyEnum.HEALTH_ADVICE_ARTICLE,
                                            String.valueOf(id));
        if (cachedAdvice instanceof HealthAdviceArticleResponse cached) {
            return cached;
        }
        var advice = healthAdviceDao.getById(id);
        if (advice == null) {
            throw new ClientException(HttpStatus.NOT_FOUND, "文章不存在");
        }
        var authorName = userDao.getNameById(advice.getAuthor());
        var dto = converter.toDto(advice, authorName);
        var status = redisService.set(RedisKeyEnum.HEALTH_ADVICE_ARTICLE,
                                      String.valueOf(advice.getId()), dto);
        if (status) log.warn("redis中有数据但是无法获取");
        return dto;
    }

    @Override
    public HealthAdviceArticleListResponse getByPage(long page) {
        var cachedAdviceList = redisService.get(RedisKeyEnum.HEALTH_ADVICE_PAGE,
                                                "0");
        if (cachedAdviceList instanceof HealthAdviceArticleListResponse cached) {
            return cached;
        }
        var pageData = healthAdviceDao.getPage(page, 10);
        if (pageData == null || pageData.getRecords()
                                        .isEmpty()) {
            throw new ClientException(HttpStatus.NOT_FOUND, "页面不存在");
        }
        var authorNames = userDao.getNamesByIds(pageData.getRecords()
                                                        .stream()
                                                        .map(HealthAdvice::getAuthor)
                                                        .toList());
        return converter.toDtoList(pageData.getRecords(), authorNames,
                                   pageData.getCurrent(), pageData.getSize());
    }
}
