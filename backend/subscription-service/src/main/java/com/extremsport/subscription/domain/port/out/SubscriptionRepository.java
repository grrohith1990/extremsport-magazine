package com.extremsport.subscription.domain.port.out;

import com.extremsport.subscription.domain.model.Subscription;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository {

    Subscription save(Subscription subscription);

    Optional<Subscription> findById(UUID id);

    Optional<Subscription> findActiveByUserId(UUID userId);

    List<Subscription> findByUserId(UUID userId);

    List<Subscription> findByStatus(Subscription.SubscriptionStatus status);

    boolean existsActiveByUserId(UUID userId);
}

