package com.extremsport.subscription.domain.port.out;

import com.extremsport.subscription.domain.model.Subscription;

/**
 * Secondary Port: Event publishing for subscription lifecycle events.
 */
public interface SubscriptionEventPublisher {

    void publishSubscriptionCreated(Subscription subscription);

    void publishSubscriptionCancelled(Subscription subscription);

    void publishSubscriptionRenewed(Subscription subscription);

    void publishSubscriptionExpired(Subscription subscription);

    void publishArticlePurchased(String userId, String articleId);
}

