package com.hirrao.health.common.reponse;

import java.time.LocalDateTime;

public record HealthAdviceArticleResponse(Long id, String title, String content,
                                          String image, String authorName,
                                          LocalDateTime updateTime) {
}
