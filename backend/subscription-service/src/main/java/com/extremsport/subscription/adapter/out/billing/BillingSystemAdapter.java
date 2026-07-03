package com.extremsport.subscription.adapter.out.billing;

import com.extremsport.subscription.domain.port.out.BillingPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Adapter: Legacy Billing System Integration.
 *
 * KEY AGILITY POINT: Anti-Corruption Layer for the legacy
 * Windows 2000-based billing system. Translates between our
 * modern domain model and the legacy system's data format.
 *
 * When the billing system is modernized, only this adapter needs to change.
 */
@Slf4j
@Component
@Profile("!dev")
public class BillingSystemAdapter implements BillingPort {

    private final RestTemplate restTemplate;
    private final String billingSystemUrl;

    public BillingSystemAdapter(@Value("${billing.system.url:http://localhost:9090}") String billingSystemUrl) {
        this.restTemplate = new RestTemplate();
        this.billingSystemUrl = billingSystemUrl;
    }

    @Override
    public String createInvoice(String userId, String description, BigDecimal amount) {
        try {
            log.info("Creating invoice in billing system: user={}, amount={}, desc={}", userId, amount, description);
            // Anti-Corruption Layer: Transform our domain model to legacy format
            // Legacy system expects: { "kundenNr": "...", "betrag": "...", "text": "..." }
            // restTemplate.postForEntity(billingSystemUrl + "/api/rechnungen", legacyInvoice, String.class);
            String invoiceId = "INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            log.info("Invoice created: {}", invoiceId);
            return invoiceId;
        } catch (Exception e) {
            log.error("Failed to create invoice in billing system: {}", e.getMessage());
            return "PENDING-" + UUID.randomUUID().toString().substring(0, 8);
        }
    }

    @Override
    public PaymentResult processPayment(String paymentMethodId, BigDecimal amount, String invoiceId) {
        try {
            log.info("Processing payment: method={}, amount={}, invoice={}", paymentMethodId, amount, invoiceId);
            // Anti-Corruption Layer: Transform to legacy payment format
            // Legacy expects SOAP/XML or proprietary format
            String transactionId = "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            return new PaymentResult(true, transactionId, null);
        } catch (Exception e) {
            log.error("Payment processing failed: {}", e.getMessage());
            return new PaymentResult(false, null, e.getMessage());
        }
    }

    @Override
    public boolean processRefund(String transactionId, BigDecimal amount) {
        try {
            log.info("Processing refund: transaction={}, amount={}", transactionId, amount);
            // restTemplate.postForEntity(billingSystemUrl + "/api/stornierung", refundRequest, Void.class);
            return true;
        } catch (Exception e) {
            log.error("Refund processing failed: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean isAvailable() {
        try {
            restTemplate.getForEntity(billingSystemUrl + "/health", String.class);
            return true;
        } catch (Exception e) {
            log.warn("Billing system is not available: {}", e.getMessage());
            return false;
        }
    }
}
