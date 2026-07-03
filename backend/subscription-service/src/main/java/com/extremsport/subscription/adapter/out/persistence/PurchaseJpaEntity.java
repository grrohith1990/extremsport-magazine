package com.extremsport.subscription.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "single_purchases")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private UUID articleId;

    private String articleTitle;
    private BigDecimal price;

    private String status;

    private String paymentMethodId;
    private String transactionId;
    private LocalDateTime purchasedAt;
}


