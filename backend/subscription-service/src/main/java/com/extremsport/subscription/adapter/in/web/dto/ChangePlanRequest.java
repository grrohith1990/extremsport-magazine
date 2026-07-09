package com.extremsport.subscription.adapter.in.web.dto;

import com.extremsport.subscription.domain.model.Subscription;
import jakarta.validation.constraints.NotNull;

public record ChangePlanRequest(
        @NotNull(message = "New plan is required")
        Subscription.SubscriptionPlan newPlan
) {}

