package com.hirrao.health.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hirrao.health.dao.HealthAdviceDao;
import com.hirrao.health.dao.mapper.HealthAdviceMapper;
import com.hirrao.health.entity.HealthAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class HealthAdviceDaoImpl extends ServiceImpl<HealthAdviceMapper, HealthAdvice> implements HealthAdviceDao {
}
