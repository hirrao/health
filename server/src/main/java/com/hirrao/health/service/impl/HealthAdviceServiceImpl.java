package com.hirrao.health.service.impl;

import com.hirrao.health.common.converter.HealthAdviceConverter;
import com.hirrao.health.common.exception.ClientException;
import com.hirrao.health.common.reponse.HealthAdviceArticleResponse;
import com.hirrao.health.dao.HealthAdviceDao;
import com.hirrao.health.dao.UserDao;
import com.hirrao.health.service.HealthAdviceService;
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

    @Autowired
    public HealthAdviceServiceImpl(HealthAdviceDao healthAdviceDao,
                                   UserDao userDao,
                                   HealthAdviceConverter converter) {
        this.healthAdviceDao = healthAdviceDao;
        this.userDao = userDao;
        this.converter = converter;
    }

    @Override
    public HealthAdviceArticleResponse getById(Long id) {
        var advice = healthAdviceDao.getById(id);
        if (advice == null) {
            throw new ClientException(HttpStatus.NOT_FOUND, "文章不存在");
        }
        var authorName = userDao.getNameById(advice.getAuthor());
        return converter.toDto(advice, authorName);
    }
}
