package com.extremsport.subscription.adapter.out.billing;

import com.extremsport.subscription.domain.port.out.BillingPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Component
@Profile("dev")
public class DevBillingAdapter implements BillingPort {

    @Override
    public String createInvoice(String userId, String description, BigDecimal amount) {
        String invoiceId = "DEV-INV-" + UUID.randomUUID().toString().substring(0, 8);
        log.info("[DEV BILLING] Invoice created: {} - {} - €{}", invoiceId, description, amount);
        return invoiceId;
    }

    @Override
    public PaymentResult processPayment(String paymentMethodId, BigDecimal amount, String invoiceId) {
        String txnId = "DEV-TXN-" + UUID.randomUUID().toString().substring(0, 8);
        log.info("[DEV BILLING] Payment processed: €{} for invoice {}", amount, invoiceId);
        return new PaymentResult(true, txnId, null);
    }

    @Override
    public boolean processRefund(String transactionId, BigDecimal amount) {
        log.info("[DEV BILLING] Refund processed: {} - €{}", transactionId, amount);
        return true;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}

