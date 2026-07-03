package com.extremsport.subscription.adapter.out.messaging;

import com.extremsport.subscription.domain.model.Subscription;
import com.extremsport.subscription.domain.port.out.SubscriptionEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("dev")
@Primary
public class DevSubscriptionEventPublisher implements SubscriptionEventPublisher {

    @Override
    public void publishSubscriptionCreated(Subscription subscription) {
        log.info("[DEV EVENT] Subscription created: {}", subscription.getId());
    }

    @Override
    public void publishSubscriptionCancelled(Subscription subscription) {
        log.info("[DEV EVENT] Subscription cancelled: {}", subscription.getId());
    }

    @Override
    public void publishSubscriptionRenewed(Subscription subscription) {
        log.info("[DEV EVENT] Subscription renewed: {}", subscription.getId());
    }

    @Override
    public void publishSubscriptionExpired(Subscription subscription) {
        log.info("[DEV EVENT] Subscription expired: {}", subscription.getId());
    }

    @Override
    public void publishArticlePurchased(String userId, String articleId) {
        log.info("[DEV EVENT] Article purchased: user={}, article={}", userId, articleId);
    }
}

