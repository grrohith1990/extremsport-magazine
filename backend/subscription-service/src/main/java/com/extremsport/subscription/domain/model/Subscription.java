package com.extremsport.subscription.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain Entity: Subscription
 * Represents a user's subscription (Abo) to the magazine.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    private UUID id;
    private UUID userId;
    private SubscriptionPlan plan;
    private SubscriptionStatus status;
    private BigDecimal pricePerPeriod;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime nextBillingDate;
    private boolean autoRenew;
    private String paymentMethodId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime cancelledAt;

    public enum SubscriptionPlan {
        MONTHLY(BigDecimal.valueOf(9.99), "Monatlich"),
        YEARLY(BigDecimal.valueOf(89.99), "Jährlich"),
        PREMIUM(BigDecimal.valueOf(14.99), "Premium Monatlich");

        private final BigDecimal defaultPrice;
        private final String displayName;

        SubscriptionPlan(BigDecimal defaultPrice, String displayName) {
            this.defaultPrice = defaultPrice;
            this.displayName = displayName;
        }

        public BigDecimal getDefaultPrice() { return defaultPrice; }
        public String getDisplayName() { return displayName; }
    }

    public enum SubscriptionStatus {
        ACTIVE,
        CANCELLED,
        EXPIRED,
        TRIAL,
        PAYMENT_FAILED,
        SUSPENDED
    }

    public boolean isActive() {
        return status == SubscriptionStatus.ACTIVE || status == SubscriptionStatus.TRIAL;
    }

    public void cancel() {
        this.status = SubscriptionStatus.CANCELLED;
        this.autoRenew = false;
        this.cancelledAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void renew() {
        this.startDate = this.endDate;
        this.endDate = calculateNextEndDate();
        this.nextBillingDate = this.endDate;
        this.status = SubscriptionStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    public void suspend() {
        this.status = SubscriptionStatus.SUSPENDED;
        this.updatedAt = LocalDateTime.now();
    }

    public void expire() {
        this.status = SubscriptionStatus.EXPIRED;
        this.updatedAt = LocalDateTime.now();
    }

    private LocalDateTime calculateNextEndDate() {
        return switch (plan) {
            case MONTHLY, PREMIUM -> endDate.plusMonths(1);
            case YEARLY -> endDate.plusYears(1);
        };
    }
}

