package com.extremsport.article.adapter.out.persistence;

import com.extremsport.article.domain.model.Article;
import com.extremsport.article.domain.port.out.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Secondary Adapter (Driven): JPA-based persistence implementation.
 * Implements the ArticleRepository port.
 *
 * KEY AGILITY POINT: This adapter can be replaced (e.g., with a MongoDB adapter)
 * without changing the domain or application layer.
 */
@Component
@RequiredArgsConstructor
public class ArticlePersistenceAdapter implements ArticleRepository {

    private final ArticleJpaRepository jpaRepository;
    private final ArticleMapper mapper;

    @Override
    public Article save(Article article) {
        ArticleJpaEntity entity = mapper.toJpaEntity(article);
        ArticleJpaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Article> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Article> findByAccessType(Article.AccessType accessType, int page, int size) {
        var jpaAccessType = ArticleJpaEntity.AccessTypeJpa.valueOf(accessType.name());
        return jpaRepository.findByAccessType(jpaAccessType, PageRequest.of(page, size))
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Article> findByStatusAndArchived(Article.ArticleStatus status, boolean archived, int page, int size) {
        var jpaStatus = ArticleJpaEntity.ArticleStatusJpa.valueOf(status.name());
        return jpaRepository.findByStatusAndArchived(jpaStatus, archived, PageRequest.of(page, size))
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Article> findByAuthorId(UUID authorId) {
        return jpaRepository.findByAuthorId(authorId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Article> search(String query, int page, int size) {
        return jpaRepository.search(query, PageRequest.of(page, size))
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}

