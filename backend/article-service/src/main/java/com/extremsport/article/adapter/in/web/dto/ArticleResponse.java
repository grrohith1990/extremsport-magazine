package com.extremsport.article.adapter.in.web.dto;

import com.extremsport.article.domain.model.Article;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Response DTO for article data sent to the client.
 * Only exposes fields that the frontend needs — hides internal domain details.
 */
public record ArticleResponse(
        UUID id,
        String title,
        String subtitle,
        String content,
        String summary,
        UUID authorId,
        String authorName,
        String status,
        String accessType,
        List<String> tags,
        String category,
        String coverImageUrl,
        LocalDateTime createdAt,
        LocalDateTime publishedAt
) {

    /**
     * Maps a domain Article to the response DTO.
     */
    public static ArticleResponse from(Article article) {
        return new ArticleResponse(
                article.getId(),
                article.getTitle(),
                article.getSubtitle(),
                article.getContent(),
                article.getSummary(),
                article.getAuthorId(),
                article.getAuthorName(),
                article.getStatus() != null ? article.getStatus().name() : null,
                article.getAccessType() != null ? article.getAccessType().name() : null,
                article.getTags(),
                article.getCategory(),
                article.getCoverImageUrl(),
                article.getCreatedAt(),
                article.getPublishedAt()
        );
    }

    /**
     * Maps a list of domain Articles to response DTOs.
     */
    public static List<ArticleResponse> from(List<Article> articles) {
        return articles.stream().map(ArticleResponse::from).toList();
    }
}

