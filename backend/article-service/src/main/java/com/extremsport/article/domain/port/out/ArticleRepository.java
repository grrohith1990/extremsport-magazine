package com.extremsport.article.domain.port.out;

import com.extremsport.article.domain.model.Article;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Secondary Port (Driven): Persistence abstraction.
 * This interface defines what the application NEEDS from external systems.
 * Implemented by adapters (e.g., JPA, MongoDB, etc.)
 *
 * KEY AGILITY POINT: By abstracting persistence behind this port,
 * the database technology can be swapped without affecting business logic.
 */
public interface ArticleRepository {

    Article save(Article article);

    Optional<Article> findById(UUID id);

    List<Article> findByAccessType(Article.AccessType accessType, int page, int size);

    List<Article> findByStatusAndArchived(Article.ArticleStatus status, boolean archived, int page, int size);

    List<Article> findByAuthorId(UUID authorId);

    List<Article> search(String query, int page, int size);

    void deleteById(UUID id);
}

