package com.extremsport.subscription.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PurchaseArticleRequest(
        @NotNull(message = "User ID is required")
        UUID userId,

        @NotNull(message = "Article ID is required")
        UUID articleId,

        @NotBlank(message = "Article title is required")
        String articleTitle,

        @NotNull(message = "Payment method ID is required")
        String paymentMethodId
) {}

