package com.hirrao.health.common.converter;

import com.hirrao.health.common.response.HealthAdviceArticleListResponse;
import com.hirrao.health.common.response.HealthAdviceArticleResponse;
import com.hirrao.health.entity.HealthAdvice;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface HealthAdviceConverter {
    @Mapping(source = "authorName", target = "authorName")
    HealthAdviceArticleResponse toDto(HealthAdvice healthAdvice,
                                      String authorName);

    default HealthAdviceArticleListResponse toDtoList(
            List<HealthAdvice> healthAdviceList,
            @Context Map<Long, String> authorNameList, Long pageNum,
            Long pageSize) {
        if (healthAdviceList == null || healthAdviceList.isEmpty()) {
            return new HealthAdviceArticleListResponse(Collections.emptyList(),
                                                       "0", "0");
        }
        return new HealthAdviceArticleListResponse(healthAdviceList.stream()
                                                                   .map(it -> {
                                                                       String authorName = authorNameList.get(
                                                                               it.getAuthor());
                                                                       return toDto(
                                                                               it,
                                                                               authorName);
                                                                   })
                                                                   .collect(
                                                                           Collectors.toList()),
                                                   String.valueOf(pageNum),
                                                   String.valueOf(pageSize));
    }
}
