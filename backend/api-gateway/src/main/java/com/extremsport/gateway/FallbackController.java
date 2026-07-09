package com.extremsport.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Fallback controller for circuit breaker.
 * Returns a friendly response when a downstream service is unavailable.
 */
@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/articles")
    public ResponseEntity<Map<String, Object>> articlesFallback() {
        return buildFallbackResponse("Article Service");
    }

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> usersFallback() {
        return buildFallbackResponse("User Service");
    }

    @GetMapping("/forum")
    public ResponseEntity<Map<String, Object>> forumFallback() {
        return buildFallbackResponse("Forum Service");
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<Map<String, Object>> subscriptionsFallback() {
        return buildFallbackResponse("Subscription Service");
    }

    private ResponseEntity<Map<String, Object>> buildFallbackResponse(String serviceName) {
        Map<String, Object> response = Map.of(
                "status", "SERVICE_UNAVAILABLE",
                "message", serviceName + " is currently unavailable. Please try again later.",
                "timestamp", LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
}

