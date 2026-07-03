package com.extremsport.article.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleTest {

    @Test
    void publish_shouldSetStatusToPublishedAndSetPublishedAt() {
        Article article = Article.builder()
                .id(UUID.randomUUID())
                .title("Test Article")
                .status(Article.ArticleStatus.DRAFT)
                .build();

        article.publish();

        assertThat(article.getStatus()).isEqualTo(Article.ArticleStatus.PUBLISHED);
        assertThat(article.getPublishedAt()).isNotNull();
        assertThat(article.getPublishedAt()).isBeforeOrEqualTo(LocalDateTime.now());
    }

    @Test
    void archive_shouldSetStatusToArchivedAndMarkAsArchived() {
        Article article = Article.builder()
                .id(UUID.randomUUID())
                .title("Test Article")
                .status(Article.ArticleStatus.PUBLISHED)
                .archived(false)
                .build();

        article.archive();

        assertThat(article.getStatus()).isEqualTo(Article.ArticleStatus.ARCHIVED);
        assertThat(article.isArchived()).isTrue();
    }

    @Test
    void isAccessibleForFree_shouldReturnTrueForPublicArticles() {
        Article article = Article.builder()
                .accessType(Article.AccessType.PUBLIC)
                .build();

        assertThat(article.isAccessibleForFree()).isTrue();
    }

    @Test
    void isAccessibleForFree_shouldReturnFalseForPremiumArticles() {
        Article article = Article.builder()
                .accessType(Article.AccessType.PREMIUM)
                .build();

        assertThat(article.isAccessibleForFree()).isFalse();
    }

    @Test
    void isAccessibleForFree_shouldReturnFalseForExclusiveArticles() {
        Article article = Article.builder()
                .accessType(Article.AccessType.EXCLUSIVE)
                .build();

        assertThat(article.isAccessibleForFree()).isFalse();
    }

    @Test
    void requiresSubscription_shouldReturnTrueForExclusiveArticles() {
        Article article = Article.builder()
                .accessType(Article.AccessType.EXCLUSIVE)
                .build();

        assertThat(article.requiresSubscription()).isTrue();
    }

    @Test
    void requiresSubscription_shouldReturnFalseForPublicArticles() {
        Article article = Article.builder()
                .accessType(Article.AccessType.PUBLIC)
                .build();

        assertThat(article.requiresSubscription()).isFalse();
    }

    @Test
    void requiresSubscription_shouldReturnFalseForPremiumArticles() {
        Article article = Article.builder()
                .accessType(Article.AccessType.PREMIUM)
                .build();

        assertThat(article.requiresSubscription()).isFalse();
    }

    @Test
    void builder_shouldCreateArticleWithAllFields() {
        UUID id = UUID.randomUUID();
        UUID authorId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        Article article = Article.builder()
                .id(id)
                .title("Extreme Surfing Guide")
                .subtitle("Catch the biggest waves")
                .content("Full content here...")
                .summary("A guide to extreme surfing")
                .authorId(authorId)
                .authorName("John Doe")
                .status(Article.ArticleStatus.DRAFT)
                .accessType(Article.AccessType.PUBLIC)
                .tags(List.of("surfing", "extreme"))
                .category("Water Sports")
                .coverImageUrl("https://example.com/image.jpg")
                .createdAt(now)
                .updatedAt(now)
                .archived(false)
                .build();

        assertThat(article.getId()).isEqualTo(id);
        assertThat(article.getTitle()).isEqualTo("Extreme Surfing Guide");
        assertThat(article.getSubtitle()).isEqualTo("Catch the biggest waves");
        assertThat(article.getAuthorId()).isEqualTo(authorId);
        assertThat(article.getTags()).containsExactly("surfing", "extreme");
        assertThat(article.getCategory()).isEqualTo("Water Sports");
        assertThat(article.isArchived()).isFalse();
    }
}

