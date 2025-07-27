package com.hirrao.health.service;

import com.hirrao.health.common.response.HealthAdviceArticleListResponse;
import com.hirrao.health.common.response.HealthAdviceArticleResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface HealthAdviceService {
    HealthAdviceArticleResponse getById(Long id);

    HealthAdviceArticleListResponse getByPage(long page);

    String createArticle(String title, String content, String imageUrl,
                       Long authorId);

    void updateArticle(Long id, String title, String content, String imageUrl);

    String uploadImage(MultipartFile file);
}
