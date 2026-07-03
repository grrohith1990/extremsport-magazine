package com.extremsport.article.domain.port.in;

import com.extremsport.article.domain.model.Article;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Primary Port (Driving): Use cases for article management.
 * This interface defines what the application CAN DO.
 * Implemented by the application service layer.
 */
public interface ArticleUseCase {

    Article createArticle(CreateArticleCommand command);

    Article updateArticle(UUID articleId, UpdateArticleCommand command);

    void publishArticle(UUID articleId);

    void archiveArticle(UUID articleId);

    Optional<Article> getArticleById(UUID articleId);

    List<Article> getPublicArticles(int page, int size);

    List<Article> getPremiumArticles(int page, int size);

    List<Article> getArchivedArticles(int page, int size);

    List<Article> searchArticles(String query, int page, int size);

    List<Article> getArticlesByAuthor(UUID authorId);

    record CreateArticleCommand(
            String title,
            String subtitle,
            String content,
            String summary,
            UUID authorId,
            Article.AccessType accessType,
            List<String> tags,
            String category,
            String coverImageUrl
    ) {}

    record UpdateArticleCommand(
            String title,
            String subtitle,
            String content,
            String summary,
            Article.AccessType accessType,
            List<String> tags,
            String category,
            String coverImageUrl
    ) {}
}

