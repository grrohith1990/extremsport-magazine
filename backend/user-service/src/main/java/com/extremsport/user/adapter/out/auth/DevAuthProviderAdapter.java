package com.extremsport.user.adapter.out.auth;

import com.extremsport.user.domain.model.User;
import com.extremsport.user.domain.port.out.AuthenticationPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("dev")
public class DevAuthProviderAdapter implements AuthenticationPort {

    @Override
    public void syncUserToAuthProvider(User user) {
        log.info("[DEV AUTH] User synced: {}", user.getUsername());
    }

    @Override
    public void removeUserFromAuthProvider(String externalUserId) {
        log.info("[DEV AUTH] User removed: {}", externalUserId);
    }

    @Override
    public void assignRoleInAuthProvider(String externalUserId, String role) {
        log.info("[DEV AUTH] Role assigned: {} -> {}", externalUserId, role);
    }

    @Override
    public void revokeRoleInAuthProvider(String externalUserId, String role) {
        log.info("[DEV AUTH] Role revoked: {} -> {}", externalUserId, role);
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}

