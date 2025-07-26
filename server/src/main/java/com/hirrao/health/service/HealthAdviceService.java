package com.hirrao.health.service;

import com.hirrao.health.common.response.HealthAdviceArticleResponse;
import org.springframework.stereotype.Service;

@Service
public interface HealthAdviceService {
    HealthAdviceArticleResponse getById(Long id);
}
