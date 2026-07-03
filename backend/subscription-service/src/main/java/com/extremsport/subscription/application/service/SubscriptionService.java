package com.extremsport.subscription.application.service;

import com.extremsport.subscription.domain.model.SinglePurchase;
import com.extremsport.subscription.domain.model.Subscription;
import com.extremsport.subscription.domain.port.in.SubscriptionUseCase;
import com.extremsport.subscription.domain.port.out.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionService implements SubscriptionUseCase {

    private final SubscriptionRepository subscriptionRepository;
    private final PurchaseRepository purchaseRepository;
    private final BillingPort billingPort;
    private final SubscriptionEventPublisher eventPublisher;

    private static final BigDecimal SINGLE_ARTICLE_PRICE = BigDecimal.valueOf(2.99);

    @Override
    public Subscription createSubscription(CreateSubscriptionCommand command) {
        // Check if user already has active subscription
        if (subscriptionRepository.existsActiveByUserId(command.userId())) {
            throw new IllegalStateException("User already has an active subscription");
        }

        Subscription.SubscriptionPlan plan = command.plan();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endDate = switch (plan) {
            case MONTHLY, PREMIUM -> now.plusMonths(1);
            case YEARLY -> now.plusYears(1);
        };

        Subscription subscription = Subscription.builder()
                .id(UUID.randomUUID())
                .userId(command.userId())
                .plan(plan)
                .status(Subscription.SubscriptionStatus.ACTIVE)
                .pricePerPeriod(plan.getDefaultPrice())
                .startDate(now)
                .endDate(endDate)
                .nextBillingDate(endDate)
                .autoRenew(command.autoRenew())
                .paymentMethodId(command.paymentMethodId())
                .createdAt(now)
                .updatedAt(now)
                .build();

        // Process initial payment through billing system
        if (billingPort.isAvailable()) {
            String invoiceId = billingPort.createInvoice(
                    command.userId().toString(),
                    "Subscription: " + plan.getDisplayName(),
                    plan.getDefaultPrice()
            );
            BillingPort.PaymentResult result = billingPort.processPayment(
                    command.paymentMethodId(), plan.getDefaultPrice(), invoiceId
            );
            if (!result.success()) {
                throw new PaymentFailedException("Payment failed: " + result.errorMessage());
            }
        }

        Subscription saved = subscriptionRepository.save(subscription);
        eventPublisher.publishSubscriptionCreated(saved);
        log.info("Subscription created: {} for user {} (plan: {})", saved.getId(), command.userId(), plan);
        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Subscription> getSubscriptionById(UUID subscriptionId) {
        return subscriptionRepository.findById(subscriptionId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Subscription> getActiveSubscriptionByUserId(UUID userId) {
        return subscriptionRepository.findActiveByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subscription> getSubscriptionsByUserId(UUID userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    @Override
    public void cancelSubscription(UUID subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException(subscriptionId));

        subscription.cancel();
        subscriptionRepository.save(subscription);
        eventPublisher.publishSubscriptionCancelled(subscription);
        log.info("Subscription cancelled: {}", subscriptionId);
    }

    @Override
    public void renewSubscription(UUID subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException(subscriptionId));

        if (!subscription.isAutoRenew()) {
            throw new IllegalStateException("Subscription is not set to auto-renew");
        }

        // Process renewal payment
        if (billingPort.isAvailable()) {
            String invoiceId = billingPort.createInvoice(
                    subscription.getUserId().toString(),
                    "Renewal: " + subscription.getPlan().getDisplayName(),
                    subscription.getPricePerPeriod()
            );
            BillingPort.PaymentResult result = billingPort.processPayment(
                    subscription.getPaymentMethodId(), subscription.getPricePerPeriod(), invoiceId
            );
            if (!result.success()) {
                subscription.suspend();
                subscriptionRepository.save(subscription);
                throw new PaymentFailedException("Renewal payment failed: " + result.errorMessage());
            }
        }

        subscription.renew();
        subscriptionRepository.save(subscription);
        eventPublisher.publishSubscriptionRenewed(subscription);
        log.info("Subscription renewed: {}", subscriptionId);
    }

    @Override
    public void changePlan(UUID subscriptionId, Subscription.SubscriptionPlan newPlan) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException(subscriptionId));

        subscription.setPlan(newPlan);
        subscription.setPricePerPeriod(newPlan.getDefaultPrice());
        subscription.setUpdatedAt(LocalDateTime.now());
        subscriptionRepository.save(subscription);
        log.info("Subscription plan changed: {} -> {}", subscriptionId, newPlan);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasActiveSubscription(UUID userId) {
        return subscriptionRepository.existsActiveByUserId(userId);
    }

    // === Single Purchase ===

    @Override
    public SinglePurchase purchaseArticle(PurchaseArticleCommand command) {
        // Check if already purchased
        if (purchaseRepository.existsByUserIdAndArticleId(command.userId(), command.articleId())) {
            throw new IllegalStateException("Article already purchased by this user");
        }

        SinglePurchase purchase = SinglePurchase.builder()
                .id(UUID.randomUUID())
                .userId(command.userId())
                .articleId(command.articleId())
                .articleTitle(command.articleTitle())
                .price(SINGLE_ARTICLE_PRICE)
                .status(SinglePurchase.PurchaseStatus.PENDING)
                .paymentMethodId(command.paymentMethodId())
                .purchasedAt(LocalDateTime.now())
                .build();

        // Process payment
        if (billingPort.isAvailable()) {
            String invoiceId = billingPort.createInvoice(
                    command.userId().toString(),
                    "Article: " + command.articleTitle(),
                    SINGLE_ARTICLE_PRICE
            );
            BillingPort.PaymentResult result = billingPort.processPayment(
                    command.paymentMethodId(), SINGLE_ARTICLE_PRICE, invoiceId
            );
            if (result.success()) {
                purchase.complete(result.transactionId());
            } else {
                purchase.setStatus(SinglePurchase.PurchaseStatus.FAILED);
                purchaseRepository.save(purchase);
                throw new PaymentFailedException("Article purchase payment failed: " + result.errorMessage());
            }
        } else {
            // Graceful degradation: allow purchase, process billing later
            purchase.complete("PENDING-" + UUID.randomUUID());
        }

        SinglePurchase saved = purchaseRepository.save(purchase);
        eventPublisher.publishArticlePurchased(command.userId().toString(), command.articleId().toString());
        log.info("Article purchased: {} by user {}", command.articleId(), command.userId());
        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SinglePurchase> getPurchasesByUserId(UUID userId) {
        return purchaseRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasAccessToArticle(UUID userId, UUID articleId) {
        // User has access if they have an active subscription OR purchased the article
        return subscriptionRepository.existsActiveByUserId(userId)
                || purchaseRepository.existsByUserIdAndArticleId(userId, articleId);
    }
}

