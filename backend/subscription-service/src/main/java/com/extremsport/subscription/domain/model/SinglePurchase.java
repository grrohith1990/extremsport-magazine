package com.extremsport.subscription.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain Entity: SinglePurchase
 * Represents a one-time purchase of a premium article.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SinglePurchase {

    private UUID id;
    private UUID userId;
    private UUID articleId;
    private String articleTitle;
    private BigDecimal price;
    private PurchaseStatus status;
    private String paymentMethodId;
    private String transactionId;
    private LocalDateTime purchasedAt;

    public enum PurchaseStatus {
        PENDING,
        COMPLETED,
        FAILED,
        REFUNDED
    }

    public boolean isCompleted() {
        return status == PurchaseStatus.COMPLETED;
    }

    public void complete(String transactionId) {
        this.status = PurchaseStatus.COMPLETED;
        this.transactionId = transactionId;
    }

    public void refund() {
        this.status = PurchaseStatus.REFUNDED;
    }
}

