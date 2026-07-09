package com.extremsport.subscription.adapter.in.web.dto;

import com.extremsport.subscription.domain.model.SinglePurchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PurchaseResponse(
        UUID id,
        UUID userId,
        UUID articleId,
        String articleTitle,
        BigDecimal price,
        String status,
        LocalDateTime purchasedAt
) {
    public static PurchaseResponse from(SinglePurchase purchase) {
        return new PurchaseResponse(
                purchase.getId(),
                purchase.getUserId(),
                purchase.getArticleId(),
                purchase.getArticleTitle(),
                purchase.getPrice(),
                purchase.getStatus() != null ? purchase.getStatus().name() : null,
                purchase.getPurchasedAt()
        );
    }

    public static List<PurchaseResponse> from(List<SinglePurchase> purchases) {
        return purchases.stream().map(PurchaseResponse::from).toList();
    }
}

