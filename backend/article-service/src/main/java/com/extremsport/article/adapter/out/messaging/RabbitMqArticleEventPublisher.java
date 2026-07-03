package com.extremsport.article.adapter.out.messaging;

import com.extremsport.article.domain.model.Article;
import com.extremsport.article.domain.port.out.ArticleEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Secondary Adapter: RabbitMQ event publisher.
 * Publishes domain events for other services to consume.
 */
@Slf4j
@Component
@RequiredArgsConstructor
@Profile("!dev")
public class RabbitMqArticleEventPublisher implements ArticleEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "article-events";

    @Override
    public void publishArticleCreated(Article article) {
        log.info("Publishing article.created event for: {}", article.getId());
        rabbitTemplate.convertAndSend(EXCHANGE, "article.created", article);
    }

    @Override
    public void publishArticlePublished(Article article) {
        log.info("Publishing article.published event for: {}", article.getId());
        rabbitTemplate.convertAndSend(EXCHANGE, "article.published", article);
    }

    @Override
    public void publishArticleArchived(Article article) {
        log.info("Publishing article.archived event for: {}", article.getId());
        rabbitTemplate.convertAndSend(EXCHANGE, "article.archived", article);
    }
}

