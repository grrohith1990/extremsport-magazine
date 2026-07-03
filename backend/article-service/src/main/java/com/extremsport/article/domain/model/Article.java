package com.extremsport.article.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Domain Entity: Article
 * Core business object - independent of infrastructure concerns.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private UUID id;
    private String title;
    private String subtitle;
    private String content;
    private String summary;
    private UUID authorId;
    private String authorName;
    private ArticleStatus status;
    private AccessType accessType;
    private List<String> tags;
    private String category;
    private String coverImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
    private boolean archived;

    public enum ArticleStatus {
        DRAFT, IN_REVIEW, PUBLISHED, ARCHIVED
    }

    public enum AccessType {
        PUBLIC,      // Free for everyone
        PREMIUM,    // Requires subscription or single purchase
        EXCLUSIVE   // Only available via subscription
    }

    public boolean isAccessibleForFree() {
        return this.accessType == AccessType.PUBLIC;
    }

    public boolean requiresSubscription() {
        return this.accessType == AccessType.EXCLUSIVE;
    }

    public void publish() {
        this.status = ArticleStatus.PUBLISHED;
        this.publishedAt = LocalDateTime.now();
    }

    public void archive() {
        this.status = ArticleStatus.ARCHIVED;
        this.archived = true;
    }
}

