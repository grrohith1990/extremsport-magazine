package com.extremsport.subscription.adapter.in.web;

import com.extremsport.subscription.adapter.in.web.dto.*;
import com.extremsport.subscription.domain.port.in.SubscriptionUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionUseCase subscriptionUseCase;

    // === Subscription Endpoints ===

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionResponse createSubscription(@Valid @RequestBody CreateSubscriptionRequest request) {
        return SubscriptionResponse.from(subscriptionUseCase.createSubscription(new SubscriptionUseCase.CreateSubscriptionCommand(
                request.userId(),
                request.plan(),
                request.paymentMethodId(),
                request.autoRenew()
        )));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SubscriptionResponse> getSubscription(@PathVariable UUID id) {
        return subscriptionUseCase.getSubscriptionById(id)
                .map(sub -> ResponseEntity.ok(SubscriptionResponse.from(sub)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public List<SubscriptionResponse> getSubscriptionsByUser(@PathVariable UUID userId) {
        return SubscriptionResponse.from(subscriptionUseCase.getSubscriptionsByUserId(userId));
    }

    @GetMapping("/user/{userId}/active")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SubscriptionResponse> getActiveSubscription(@PathVariable UUID userId) {
        return subscriptionUseCase.getActiveSubscriptionByUserId(userId)
                .map(sub -> ResponseEntity.ok(SubscriptionResponse.from(sub)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}/has-access")
    public ResponseEntity<Boolean> hasActiveSubscription(@PathVariable UUID userId) {
        return ResponseEntity.ok(subscriptionUseCase.hasActiveSubscription(userId));
    }

    @PostMapping("/{id}/cancel")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> cancelSubscription(@PathVariable UUID id) {
        subscriptionUseCase.cancelSubscription(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/renew")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> renewSubscription(@PathVariable UUID id) {
        subscriptionUseCase.renewSubscription(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/plan")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> changePlan(@PathVariable UUID id, @Valid @RequestBody ChangePlanRequest request) {
        subscriptionUseCase.changePlan(id, request.newPlan());
        return ResponseEntity.ok().build();
    }

    // === Single Purchase Endpoints ===

    @PostMapping("/purchases")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseResponse purchaseArticle(@Valid @RequestBody PurchaseArticleRequest request) {
        return PurchaseResponse.from(subscriptionUseCase.purchaseArticle(new SubscriptionUseCase.PurchaseArticleCommand(
                request.userId(),
                request.articleId(),
                request.articleTitle(),
                request.paymentMethodId()
        )));
    }

    @GetMapping("/purchases/user/{userId}")
    @PreAuthorize("isAuthenticated()")
    public List<PurchaseResponse> getPurchasesByUser(@PathVariable UUID userId) {
        return PurchaseResponse.from(subscriptionUseCase.getPurchasesByUserId(userId));
    }

    @GetMapping("/access/{userId}/{articleId}")
    public ResponseEntity<Boolean> hasAccessToArticle(@PathVariable UUID userId, @PathVariable UUID articleId) {
        return ResponseEntity.ok(subscriptionUseCase.hasAccessToArticle(userId, articleId));
    }
}
