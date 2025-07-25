package com.hirrao.health.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hirrao.health.entity.HealthAdvice;
import org.springframework.stereotype.Service;

@Service
public interface HealthAdviceDao extends IService<HealthAdvice> {
    IPage<HealthAdvice> getPage(long page, long size);
}
