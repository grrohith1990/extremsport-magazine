package com.extremsport.subscription.adapter.out.persistence;

import com.extremsport.subscription.domain.model.Subscription;
import com.extremsport.subscription.domain.port.out.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SubscriptionPersistenceAdapter implements SubscriptionRepository {

    private final SubscriptionJpaRepository jpaRepository;

    @Override
    public Subscription save(Subscription subscription) {
        SubscriptionJpaEntity entity = toEntity(subscription);
        SubscriptionJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Subscription> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Subscription> findActiveByUserId(UUID userId) {
        return jpaRepository.findActiveByUserId(userId).map(this::toDomain);
    }

    @Override
    public List<Subscription> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId).stream().map(this::toDomain).toList();
    }

    @Override
    public List<Subscription> findByStatus(Subscription.SubscriptionStatus status) {
        return jpaRepository.findByStatus(status.name()).stream().map(this::toDomain).toList();
    }

    @Override
    public boolean existsActiveByUserId(UUID userId) {
        return jpaRepository.existsActiveByUserId(userId);
    }

    private Subscription toDomain(SubscriptionJpaEntity entity) {
        return Subscription.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .plan(Subscription.SubscriptionPlan.valueOf(entity.getPlan()))
                .status(Subscription.SubscriptionStatus.valueOf(entity.getStatus()))
                .pricePerPeriod(entity.getPricePerPeriod())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .nextBillingDate(entity.getNextBillingDate())
                .autoRenew(entity.isAutoRenew())
                .paymentMethodId(entity.getPaymentMethodId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .cancelledAt(entity.getCancelledAt())
                .build();
    }

    private SubscriptionJpaEntity toEntity(Subscription sub) {
        return SubscriptionJpaEntity.builder()
                .id(sub.getId())
                .userId(sub.getUserId())
                .plan(sub.getPlan().name())
                .status(sub.getStatus().name())
                .pricePerPeriod(sub.getPricePerPeriod())
                .startDate(sub.getStartDate())
                .endDate(sub.getEndDate())
                .nextBillingDate(sub.getNextBillingDate())
                .autoRenew(sub.isAutoRenew())
                .paymentMethodId(sub.getPaymentMethodId())
                .createdAt(sub.getCreatedAt())
                .updatedAt(sub.getUpdatedAt())
                .cancelledAt(sub.getCancelledAt())
                .build();
    }
}

