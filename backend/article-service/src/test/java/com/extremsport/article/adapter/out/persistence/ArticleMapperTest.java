package com.extremsport.article.adapter.out.persistence;

import com.extremsport.article.domain.model.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleMapperTest {

    private ArticleMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ArticleMapper();
    }

    @Test
    void toDomain_shouldMapAllFieldsCorrectly() {
        UUID id = UUID.randomUUID();
        UUID authorId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        ArticleJpaEntity entity = ArticleJpaEntity.builder()
                .id(id)
                .title("Extreme Climbing")
                .subtitle("Reach the summit")
                .content("Full article content")
                .summary("A climbing guide")
                .authorId(authorId)
                .authorName("Jane Doe")
                .status(ArticleJpaEntity.ArticleStatusJpa.PUBLISHED)
                .accessType(ArticleJpaEntity.AccessTypeJpa.PREMIUM)
                .tags(List.of("climbing", "mountain"))
                .category("Mountain Sports")
                .coverImageUrl("https://example.com/climb.jpg")
                .createdAt(now.minusDays(5))
                .updatedAt(now.minusDays(1))
                .publishedAt(now.minusDays(2))
                .archived(false)
                .build();

        Article domain = mapper.toDomain(entity);

        assertThat(domain.getId()).isEqualTo(id);
        assertThat(domain.getTitle()).isEqualTo("Extreme Climbing");
        assertThat(domain.getSubtitle()).isEqualTo("Reach the summit");
        assertThat(domain.getContent()).isEqualTo("Full article content");
        assertThat(domain.getSummary()).isEqualTo("A climbing guide");
        assertThat(domain.getAuthorId()).isEqualTo(authorId);
        assertThat(domain.getAuthorName()).isEqualTo("Jane Doe");
        assertThat(domain.getStatus()).isEqualTo(Article.ArticleStatus.PUBLISHED);
        assertThat(domain.getAccessType()).isEqualTo(Article.AccessType.PREMIUM);
        assertThat(domain.getTags()).containsExactly("climbing", "mountain");
        assertThat(domain.getCategory()).isEqualTo("Mountain Sports");
        assertThat(domain.getCoverImageUrl()).isEqualTo("https://example.com/climb.jpg");
        assertThat(domain.getCreatedAt()).isEqualTo(now.minusDays(5));
        assertThat(domain.getUpdatedAt()).isEqualTo(now.minusDays(1));
        assertThat(domain.getPublishedAt()).isEqualTo(now.minusDays(2));
        assertThat(domain.isArchived()).isFalse();
    }

    @Test
    void toJpaEntity_shouldMapAllFieldsCorrectly() {
        UUID id = UUID.randomUUID();
        UUID authorId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        Article domain = Article.builder()
                .id(id)
                .title("Extreme Surfing")
                .subtitle("Ride the waves")
                .content("Surfing article content")
                .summary("A surfing guide")
                .authorId(authorId)
                .authorName("John Smith")
                .status(Article.ArticleStatus.DRAFT)
                .accessType(Article.AccessType.PUBLIC)
                .tags(List.of("surfing", "ocean"))
                .category("Water Sports")
                .coverImageUrl("https://example.com/surf.jpg")
                .createdAt(now.minusDays(3))
                .updatedAt(now)
                .publishedAt(null)
                .archived(false)
                .build();

        ArticleJpaEntity entity = mapper.toJpaEntity(domain);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getTitle()).isEqualTo("Extreme Surfing");
        assertThat(entity.getSubtitle()).isEqualTo("Ride the waves");
        assertThat(entity.getContent()).isEqualTo("Surfing article content");
        assertThat(entity.getSummary()).isEqualTo("A surfing guide");
        assertThat(entity.getAuthorId()).isEqualTo(authorId);
        assertThat(entity.getAuthorName()).isEqualTo("John Smith");
        assertThat(entity.getStatus()).isEqualTo(ArticleJpaEntity.ArticleStatusJpa.DRAFT);
        assertThat(entity.getAccessType()).isEqualTo(ArticleJpaEntity.AccessTypeJpa.PUBLIC);
        assertThat(entity.getTags()).containsExactly("surfing", "ocean");
        assertThat(entity.getCategory()).isEqualTo("Water Sports");
        assertThat(entity.getCoverImageUrl()).isEqualTo("https://example.com/surf.jpg");
        assertThat(entity.getCreatedAt()).isEqualTo(now.minusDays(3));
        assertThat(entity.getUpdatedAt()).isEqualTo(now);
        assertThat(entity.getPublishedAt()).isNull();
        assertThat(entity.isArchived()).isFalse();
    }

    @Test
    void roundTrip_domainToEntityAndBack_shouldPreserveAllFields() {
        UUID id = UUID.randomUUID();
        UUID authorId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        Article original = Article.builder()
                .id(id)
                .title("Round Trip Article")
                .subtitle("Testing round trip")
                .content("Content here")
                .summary("Summary here")
                .authorId(authorId)
                .authorName("Test Author")
                .status(Article.ArticleStatus.IN_REVIEW)
                .accessType(Article.AccessType.EXCLUSIVE)
                .tags(List.of("test"))
                .category("Test Category")
                .coverImageUrl("https://example.com/test.jpg")
                .createdAt(now)
                .updatedAt(now)
                .publishedAt(null)
                .archived(false)
                .build();

        Article roundTripped = mapper.toDomain(mapper.toJpaEntity(original));

        assertThat(roundTripped).usingRecursiveComparison().isEqualTo(original);
    }

    @Test
    void toDomain_shouldHandleArchivedArticle() {
        ArticleJpaEntity entity = ArticleJpaEntity.builder()
                .id(UUID.randomUUID())
                .title("Archived Article")
                .authorId(UUID.randomUUID())
                .status(ArticleJpaEntity.ArticleStatusJpa.ARCHIVED)
                .accessType(ArticleJpaEntity.AccessTypeJpa.PUBLIC)
                .archived(true)
                .createdAt(LocalDateTime.now())
                .build();

        Article domain = mapper.toDomain(entity);

        assertThat(domain.getStatus()).isEqualTo(Article.ArticleStatus.ARCHIVED);
        assertThat(domain.isArchived()).isTrue();
    }
}

