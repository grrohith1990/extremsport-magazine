package com.extremsport.article.application.service;

import com.extremsport.article.domain.model.Article;
import com.extremsport.article.domain.port.in.ArticleUseCase;
import com.extremsport.article.domain.port.out.ArticleEventPublisher;
import com.extremsport.article.domain.port.out.ArticleRepository;
import com.extremsport.article.domain.port.out.CmsPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Application Service: Orchestrates use cases.
 * Contains NO business logic - delegates to domain model.
 * Coordinates between ports (in/out).
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService implements ArticleUseCase {

    private final ArticleRepository articleRepository;
    private final CmsPort cmsPort;
    private final ArticleEventPublisher eventPublisher;

    @Override
    public Article createArticle(CreateArticleCommand command) {
        Article article = Article.builder()
                .id(UUID.randomUUID())
                .title(command.title())
                .subtitle(command.subtitle())
                .content(command.content())
                .summary(command.summary())
                .authorId(command.authorId())
                .accessType(command.accessType())
                .tags(command.tags())
                .category(command.category())
                .coverImageUrl(command.coverImageUrl())
                .status(Article.ArticleStatus.DRAFT)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .archived(false)
                .build();

        Article saved = articleRepository.save(article);

        // Sync to CMS if available (graceful degradation)
        if (cmsPort.isAvailable()) {
            cmsPort.syncArticleToCms(saved);
        }

        eventPublisher.publishArticleCreated(saved);
        log.info("Article created: {} by author {}", saved.getId(), saved.getAuthorId());
        return saved;
    }

    @Override
    public Article updateArticle(UUID articleId, UpdateArticleCommand command) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException(articleId));

        article.setTitle(command.title());
        article.setSubtitle(command.subtitle());
        article.setContent(command.content());
        article.setSummary(command.summary());
        article.setAccessType(command.accessType());
        article.setTags(command.tags());
        article.setCategory(command.category());
        article.setCoverImageUrl(command.coverImageUrl());
        article.setUpdatedAt(LocalDateTime.now());

        return articleRepository.save(article);
    }

    @Override
    public void publishArticle(UUID articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException(articleId));

        article.publish();
        articleRepository.save(article);
        eventPublisher.publishArticlePublished(article);
        log.info("Article published: {}", articleId);
    }

    @Override
    public void archiveArticle(UUID articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException(articleId));

        article.archive();
        articleRepository.save(article);
        eventPublisher.publishArticleArchived(article);
        log.info("Article archived: {}", articleId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Article> getArticleById(UUID articleId) {
        return articleRepository.findById(articleId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> getPublicArticles(int page, int size) {
        return articleRepository.findByAccessType(Article.AccessType.PUBLIC, page, size);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> getPremiumArticles(int page, int size) {
        return articleRepository.findByAccessType(Article.AccessType.PREMIUM, page, size);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> getArchivedArticles(int page, int size) {
        return articleRepository.findByStatusAndArchived(Article.ArticleStatus.ARCHIVED, true, page, size);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> searchArticles(String query, int page, int size) {
        return articleRepository.search(query, page, size);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> getArticlesByAuthor(UUID authorId) {
        return articleRepository.findByAuthorId(authorId);
    }
}

