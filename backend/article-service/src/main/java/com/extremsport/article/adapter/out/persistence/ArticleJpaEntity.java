package com.extremsport.article.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "articles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String subtitle;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 1000)
    private String summary;

    @Column(nullable = false)
    private UUID authorId;

    private String authorName;

    @Enumerated(EnumType.STRING)
    private ArticleStatusJpa status;

    @Enumerated(EnumType.STRING)
    private AccessTypeJpa accessType;

    @ElementCollection
    @CollectionTable(name = "article_tags", joinColumns = @JoinColumn(name = "article_id"))
    @Column(name = "tag")
    private List<String> tags;

    private String category;
    private String coverImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
    private boolean archived;

    public enum ArticleStatusJpa {
        DRAFT, IN_REVIEW, PUBLISHED, ARCHIVED
    }

    public enum AccessTypeJpa {
        PUBLIC, PREMIUM, EXCLUSIVE
    }
}

