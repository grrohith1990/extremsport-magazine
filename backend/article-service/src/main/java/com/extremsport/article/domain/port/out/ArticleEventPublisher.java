package com.extremsport.article.domain.port.out;

import com.extremsport.article.domain.model.Article;

/**
 * Secondary Port (Driven): Event publishing abstraction.
 * Used for asynchronous communication between services.
 */
public interface ArticleEventPublisher {

    void publishArticleCreated(Article article);

    void publishArticlePublished(Article article);

    void publishArticleArchived(Article article);
}

