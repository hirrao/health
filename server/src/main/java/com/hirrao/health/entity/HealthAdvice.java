package com.hirrao.health.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class HealthAdvice {
    @TableId
    private Long id;

    private String title;
    private String content;
    private String image;
    private Long author;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public HealthAdvice(String title, String content, String image,
                        Long author) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.author = author;
    }
}
