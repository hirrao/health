package com.hirrao.health.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hirrao.health.dao.HealthAdviceDao;
import com.hirrao.health.dao.mapper.HealthAdviceMapper;
import com.hirrao.health.entity.HealthAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class HealthAdviceDaoImpl extends ServiceImpl<HealthAdviceMapper, HealthAdvice> implements HealthAdviceDao {
    private final HealthAdviceMapper healthAdviceMapper;

    @Autowired
    public HealthAdviceDaoImpl(HealthAdviceMapper healthAdviceMapper) {
        this.healthAdviceMapper = healthAdviceMapper;
    }

    @Override
    public IPage<HealthAdvice> getPage(long page, long size) {
        var pageRequest = new Page<HealthAdvice>(page, size);
        var wrapper = new LambdaQueryWrapper<HealthAdvice>().orderByDesc(
                HealthAdvice::getUpdateTime);
        return healthAdviceMapper.selectPage(pageRequest, wrapper);
    }
}