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
@Table(name = "subscriptions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    private String plan;

    private String status;

    private BigDecimal pricePerPeriod;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime nextBillingDate;
    private boolean autoRenew;
    private String paymentMethodId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime cancelledAt;
}


