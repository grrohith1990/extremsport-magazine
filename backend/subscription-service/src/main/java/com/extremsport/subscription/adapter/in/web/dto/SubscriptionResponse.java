package com.extremsport.subscription.adapter.in.web.dto;

import com.extremsport.subscription.domain.model.Subscription;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record SubscriptionResponse(
        UUID id,
        UUID userId,
        String plan,
        String planDisplayName,
        String status,
        BigDecimal pricePerPeriod,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime nextBillingDate,
        boolean autoRenew,
        LocalDateTime createdAt
) {
    public static SubscriptionResponse from(Subscription sub) {
        return new SubscriptionResponse(
                sub.getId(),
                sub.getUserId(),
                sub.getPlan() != null ? sub.getPlan().name() : null,
                sub.getPlan() != null ? sub.getPlan().getDisplayName() : null,
                sub.getStatus() != null ? sub.getStatus().name() : null,
                sub.getPricePerPeriod(),
                sub.getStartDate(),
                sub.getEndDate(),
                sub.getNextBillingDate(),
                sub.isAutoRenew(),
                sub.getCreatedAt()
        );
    }

    public static List<SubscriptionResponse> from(List<Subscription> subscriptions) {
        return subscriptions.stream().map(SubscriptionResponse::from).toList();
    }
}

