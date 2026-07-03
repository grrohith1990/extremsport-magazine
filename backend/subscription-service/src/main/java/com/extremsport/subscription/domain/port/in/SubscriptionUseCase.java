package com.extremsport.subscription.domain.port.in;

import com.extremsport.subscription.domain.model.SinglePurchase;
import com.extremsport.subscription.domain.model.Subscription;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Primary Port: Subscription & payment use cases.
 */
public interface SubscriptionUseCase {

    // === Subscription Management ===

    Subscription createSubscription(CreateSubscriptionCommand command);

    Optional<Subscription> getSubscriptionById(UUID subscriptionId);

    Optional<Subscription> getActiveSubscriptionByUserId(UUID userId);

    List<Subscription> getSubscriptionsByUserId(UUID userId);

    void cancelSubscription(UUID subscriptionId);

    void renewSubscription(UUID subscriptionId);

    void changePlan(UUID subscriptionId, Subscription.SubscriptionPlan newPlan);

    boolean hasActiveSubscription(UUID userId);

    // === Single Purchase ===

    SinglePurchase purchaseArticle(PurchaseArticleCommand command);

    List<SinglePurchase> getPurchasesByUserId(UUID userId);

    boolean hasAccessToArticle(UUID userId, UUID articleId);

    // === Commands ===

    record CreateSubscriptionCommand(
            UUID userId,
            Subscription.SubscriptionPlan plan,
            String paymentMethodId,
            boolean autoRenew
    ) {}

    record PurchaseArticleCommand(
            UUID userId,
            UUID articleId,
            String articleTitle,
            String paymentMethodId
    ) {}
}

