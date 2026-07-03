package com.extremsport.user.domain.port.out;

import com.extremsport.user.domain.model.User;

/**
 * Secondary Port: Authentication system abstraction.
 *
 * KEY AGILITY POINT: Abstracts the authentication server.
 * Currently AD-based, planned migration to Keycloak.
 * Only this adapter needs to change during migration.
 */
public interface AuthenticationPort {

    /**
     * Syncs user data to the external auth provider.
     */
    void syncUserToAuthProvider(User user);

    /**
     * Removes user from the auth provider.
     */
    void removeUserFromAuthProvider(String externalUserId);

    /**
     * Assigns a role in the auth provider.
     */
    void assignRoleInAuthProvider(String externalUserId, String role);

    /**
     * Revokes a role in the auth provider.
     */
    void revokeRoleInAuthProvider(String externalUserId, String role);

    /**
     * Checks connectivity to auth provider (for circuit breaker).
     */
    boolean isAvailable();
}

