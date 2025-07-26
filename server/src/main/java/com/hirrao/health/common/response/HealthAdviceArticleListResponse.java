package com.hirrao.health.common.response;

import java.util.List;

public record HealthAdviceArticleListResponse(
        List<HealthAdviceArticleResponse> articles, String pageNum,
        String pageSize) {
}
