package com.extremsport.user.adapter.out.auth;

import com.extremsport.user.domain.model.User;
import com.extremsport.user.domain.port.out.AuthenticationPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Adapter for external Authentication Server.
 *
 * KEY AGILITY POINT: This adapter currently integrates with AD-based auth.
 * When migrating to Keycloak, create a new KeycloakAuthAdapter and switch via @Profile.
 *
 * @Profile("ad") -> ActiveDirectoryAuthAdapter (this one)
 * @Profile("keycloak") -> KeycloakAuthAdapter (new implementation)
 */
@Slf4j
@Component
@Profile("!dev")
public class AuthProviderAdapter implements AuthenticationPort {

    private final RestTemplate restTemplate;
    private final String authServerUrl;

    public AuthProviderAdapter(@Value("${auth.server.url:http://localhost:8080}") String authServerUrl) {
        this.restTemplate = new RestTemplate();
        this.authServerUrl = authServerUrl;
    }

    @Override
    public void syncUserToAuthProvider(User user) {
        try {
            log.info("Syncing user {} to auth provider at {}", user.getUsername(), authServerUrl);
            // restTemplate.postForEntity(authServerUrl + "/admin/users", user, Void.class);
        } catch (Exception e) {
            log.warn("Failed to sync user to auth provider: {}", e.getMessage());
        }
    }

    @Override
    public void removeUserFromAuthProvider(String externalUserId) {
        try {
            log.info("Removing user {} from auth provider", externalUserId);
            // restTemplate.delete(authServerUrl + "/admin/users/" + externalUserId);
        } catch (Exception e) {
            log.warn("Failed to remove user from auth provider: {}", e.getMessage());
        }
    }

    @Override
    public void assignRoleInAuthProvider(String externalUserId, String role) {
        try {
            log.info("Assigning role {} to user {} in auth provider", role, externalUserId);
            // restTemplate.postForEntity(authServerUrl + "/admin/users/" + externalUserId + "/roles/" + role, null, Void.class);
        } catch (Exception e) {
            log.warn("Failed to assign role in auth provider: {}", e.getMessage());
        }
    }

    @Override
    public void revokeRoleInAuthProvider(String externalUserId, String role) {
        try {
            log.info("Revoking role {} from user {} in auth provider", role, externalUserId);
            // restTemplate.delete(authServerUrl + "/admin/users/" + externalUserId + "/roles/" + role);
        } catch (Exception e) {
            log.warn("Failed to revoke role in auth provider: {}", e.getMessage());
        }
    }

    @Override
    public boolean isAvailable() {
        try {
            restTemplate.getForEntity(authServerUrl + "/health", String.class);
            return true;
        } catch (Exception e) {
            log.warn("Auth provider is not available: {}", e.getMessage());
            return false;
        }
    }
}
