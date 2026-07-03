package com.extremsport.subscription.domain.port.out;

import com.extremsport.subscription.domain.model.SinglePurchase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PurchaseRepository {

    SinglePurchase save(SinglePurchase purchase);

    Optional<SinglePurchase> findById(UUID id);

    List<SinglePurchase> findByUserId(UUID userId);

    boolean existsByUserIdAndArticleId(UUID userId, UUID articleId);
}

