package com.extremsport.article.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ArticleJpaRepository extends JpaRepository<ArticleJpaEntity, UUID> {

    Page<ArticleJpaEntity> findByAccessType(ArticleJpaEntity.AccessTypeJpa accessType, Pageable pageable);

    Page<ArticleJpaEntity> findByStatusAndArchived(ArticleJpaEntity.ArticleStatusJpa status, boolean archived, Pageable pageable);

    List<ArticleJpaEntity> findByAuthorId(UUID authorId);

    @Query("SELECT a FROM ArticleJpaEntity a WHERE " +
            "LOWER(a.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(a.content) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(a.summary) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<ArticleJpaEntity> search(@Param("query") String query, Pageable pageable);
}

