package com.extremsport.subscription.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionJpaRepository extends JpaRepository<SubscriptionJpaEntity, UUID> {

    @Query("SELECT s FROM SubscriptionJpaEntity s WHERE s.userId = :userId AND s.status IN ('ACTIVE', 'TRIAL')")
    Optional<SubscriptionJpaEntity> findActiveByUserId(@Param("userId") UUID userId);

    List<SubscriptionJpaEntity> findByUserId(UUID userId);

    List<SubscriptionJpaEntity> findByStatus(String status);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SubscriptionJpaEntity s WHERE s.userId = :userId AND s.status IN ('ACTIVE', 'TRIAL')")
    boolean existsActiveByUserId(@Param("userId") UUID userId);
}

