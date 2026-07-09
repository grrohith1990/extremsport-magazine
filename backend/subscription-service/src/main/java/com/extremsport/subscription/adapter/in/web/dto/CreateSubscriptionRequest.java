package com.extremsport.subscription.adapter.in.web.dto;

import com.extremsport.subscription.domain.model.Subscription;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateSubscriptionRequest(
        @NotNull(message = "User ID is required")
        UUID userId,

        @NotNull(message = "Plan is required")
        Subscription.SubscriptionPlan plan,

        @NotNull(message = "Payment method ID is required")
        String paymentMethodId,

        boolean autoRenew
) {}

