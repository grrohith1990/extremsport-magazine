package com.extremsport.article.adapter.out.persistence;

import com.extremsport.article.domain.model.Article;
import org.springframework.stereotype.Component;

/**
 * Maps between domain model and JPA entity.
 * Keeps domain model free from JPA annotations.
 */
@Component
public class ArticleMapper {

    public Article toDomain(ArticleJpaEntity entity) {
        return Article.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .subtitle(entity.getSubtitle())
                .content(entity.getContent())
                .summary(entity.getSummary())
                .authorId(entity.getAuthorId())
                .authorName(entity.getAuthorName())
                .status(Article.ArticleStatus.valueOf(entity.getStatus().name()))
                .accessType(Article.AccessType.valueOf(entity.getAccessType().name()))
                .tags(entity.getTags())
                .category(entity.getCategory())
                .coverImageUrl(entity.getCoverImageUrl())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .publishedAt(entity.getPublishedAt())
                .archived(entity.isArchived())
                .build();
    }

    public ArticleJpaEntity toJpaEntity(Article domain) {
        return ArticleJpaEntity.builder()
                .id(domain.getId())
                .title(domain.getTitle())
                .subtitle(domain.getSubtitle())
                .content(domain.getContent())
                .summary(domain.getSummary())
                .authorId(domain.getAuthorId())
                .authorName(domain.getAuthorName())
                .status(ArticleJpaEntity.ArticleStatusJpa.valueOf(domain.getStatus().name()))
                .accessType(ArticleJpaEntity.AccessTypeJpa.valueOf(domain.getAccessType().name()))
                .tags(domain.getTags())
                .category(domain.getCategory())
                .coverImageUrl(domain.getCoverImageUrl())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .publishedAt(domain.getPublishedAt())
                .archived(domain.isArchived())
                .build();
    }
}

