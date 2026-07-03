package com.extremsport.subscription.domain.port.out;

import java.math.BigDecimal;

/**
 * Secondary Port: Billing system abstraction.
 *
 * KEY AGILITY POINT: Abstracts the legacy billing system
 * (Windows 2000 Server based). Uses Anti-Corruption Layer pattern
 * to isolate the modern domain from the legacy system's data model.
 */
public interface BillingPort {

    /**
     * Creates an invoice in the external billing system.
     */
    String createInvoice(String userId, String description, BigDecimal amount);

    /**
     * Processes a payment through the billing system.
     */
    PaymentResult processPayment(String paymentMethodId, BigDecimal amount, String invoiceId);

    /**
     * Processes a refund in the billing system.
     */
    boolean processRefund(String transactionId, BigDecimal amount);

    /**
     * Checks connectivity to billing system.
     */
    boolean isAvailable();

    record PaymentResult(boolean success, String transactionId, String errorMessage) {}
}

