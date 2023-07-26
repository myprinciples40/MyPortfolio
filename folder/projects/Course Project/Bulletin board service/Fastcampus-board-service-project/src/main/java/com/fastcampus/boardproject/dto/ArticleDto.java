package com.fastcampus.boardproject.dto;

import java.time.LocalDateTime;

public record ArticleDto(
        LocalDateTime modifiedAt,
        String createdBy,
        String title,
        String content,
        String hashtag
) {
    public static ArticleDto of(LocalDateTime modifiedAt, String createdBy, String title, String content, String hashtag) {
        return new ArticleDto(modifiedAt, createdBy, title, content, hashtag);
    }
}