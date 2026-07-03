package com.extremsport.article.adapter.out.messaging;

import com.extremsport.article.domain.model.Article;
import com.extremsport.article.domain.port.out.ArticleEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Dev stub: logs events instead of publishing to RabbitMQ.
 */
@Slf4j
@Component
@Profile("dev")
@Primary
public class DevArticleEventPublisher implements ArticleEventPublisher {

    @Override
    public void publishArticleCreated(Article article) {
        log.info("[DEV EVENT] Article created: {} - {}", article.getId(), article.getTitle());
    }

    @Override
    public void publishArticlePublished(Article article) {
        log.info("[DEV EVENT] Article published: {} - {}", article.getId(), article.getTitle());
    }

    @Override
    public void publishArticleArchived(Article article) {
        log.info("[DEV EVENT] Article archived: {} - {}", article.getId(), article.getTitle());
    }
}

