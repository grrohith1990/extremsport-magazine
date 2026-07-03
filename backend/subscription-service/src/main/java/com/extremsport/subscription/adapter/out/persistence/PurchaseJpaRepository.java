package com.extremsport.subscription.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PurchaseJpaRepository extends JpaRepository<PurchaseJpaEntity, UUID> {

    List<PurchaseJpaEntity> findByUserId(UUID userId);

    boolean existsByUserIdAndArticleId(UUID userId, UUID articleId);
}

