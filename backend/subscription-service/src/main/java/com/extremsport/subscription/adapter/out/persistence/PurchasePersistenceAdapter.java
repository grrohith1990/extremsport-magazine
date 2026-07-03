package com.extremsport.subscription.adapter.out.persistence;

import com.extremsport.subscription.domain.model.SinglePurchase;
import com.extremsport.subscription.domain.port.out.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PurchasePersistenceAdapter implements PurchaseRepository {

    private final PurchaseJpaRepository jpaRepository;

    @Override
    public SinglePurchase save(SinglePurchase purchase) {
        PurchaseJpaEntity entity = toEntity(purchase);
        PurchaseJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<SinglePurchase> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<SinglePurchase> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId).stream().map(this::toDomain).toList();
    }

    @Override
    public boolean existsByUserIdAndArticleId(UUID userId, UUID articleId) {
        return jpaRepository.existsByUserIdAndArticleId(userId, articleId);
    }

    private SinglePurchase toDomain(PurchaseJpaEntity entity) {
        return SinglePurchase.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .articleId(entity.getArticleId())
                .articleTitle(entity.getArticleTitle())
                .price(entity.getPrice())
                .status(SinglePurchase.PurchaseStatus.valueOf(entity.getStatus()))
                .paymentMethodId(entity.getPaymentMethodId())
                .transactionId(entity.getTransactionId())
                .purchasedAt(entity.getPurchasedAt())
                .build();
    }

    private PurchaseJpaEntity toEntity(SinglePurchase purchase) {
        return PurchaseJpaEntity.builder()
                .id(purchase.getId())
                .userId(purchase.getUserId())
                .articleId(purchase.getArticleId())
                .articleTitle(purchase.getArticleTitle())
                .price(purchase.getPrice())
                .status(purchase.getStatus().name())
                .paymentMethodId(purchase.getPaymentMethodId())
                .transactionId(purchase.getTransactionId())
                .purchasedAt(purchase.getPurchasedAt())
                .build();
    }
}

