package com.extremsport.subscription.adapter.out.messaging;

import com.extremsport.subscription.domain.model.Subscription;
import com.extremsport.subscription.domain.port.out.SubscriptionEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("!dev")
public class RabbitMqSubscriptionEventPublisher implements SubscriptionEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE = "subscription-events";

    @Override
    public void publishSubscriptionCreated(Subscription subscription) {
        log.info("Publishing subscription.created event: {}", subscription.getId());
        rabbitTemplate.convertAndSend(EXCHANGE, "subscription.created", subscription);
    }

    @Override
    public void publishSubscriptionCancelled(Subscription subscription) {
        log.info("Publishing subscription.cancelled event: {}", subscription.getId());
        rabbitTemplate.convertAndSend(EXCHANGE, "subscription.cancelled", subscription);
    }

    @Override
    public void publishSubscriptionRenewed(Subscription subscription) {
        log.info("Publishing subscription.renewed event: {}", subscription.getId());
        rabbitTemplate.convertAndSend(EXCHANGE, "subscription.renewed", subscription);
    }

    @Override
    public void publishSubscriptionExpired(Subscription subscription) {
        log.info("Publishing subscription.expired event: {}", subscription.getId());
        rabbitTemplate.convertAndSend(EXCHANGE, "subscription.expired", subscription);
    }

    @Override
    public void publishArticlePurchased(String userId, String articleId) {
        log.info("Publishing article.purchased event: user={}, article={}", userId, articleId);
        rabbitTemplate.convertAndSend(EXCHANGE, "article.purchased",
                new ArticlePurchasedEvent(userId, articleId));
    }

    record ArticlePurchasedEvent(String userId, String articleId) {}
}
