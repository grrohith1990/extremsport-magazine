package com.extremsport.subscription.application.service;

import java.util.UUID;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(UUID id) {
        super("Subscription not found: " + id);
    }
}

