package com.extremsport.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Domain Entity: User
 * Represents a registered user of the Extremsport Magazine platform.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private UUID id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String displayName;
    private String avatarUrl;
    private String bio;
    private Set<UserRole> roles;
    private boolean active;
    private boolean emailVerified;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

    public enum UserRole {
        READER,
        SUBSCRIBER,
        AUTHOR,
        EDITOR,
        MODERATOR,
        ADMIN
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isAuthor() {
        return roles != null && roles.contains(UserRole.AUTHOR);
    }

    public boolean isSubscriber() {
        return roles != null && roles.contains(UserRole.SUBSCRIBER);
    }

    public boolean isModerator() {
        return roles != null && roles.contains(UserRole.MODERATOR);
    }

    public boolean isAdmin() {
        return roles != null && roles.contains(UserRole.ADMIN);
    }

    public void grantRole(UserRole role) {
        this.roles.add(role);
        this.updatedAt = LocalDateTime.now();
    }

    public void revokeRole(UserRole role) {
        this.roles.remove(role);
        this.updatedAt = LocalDateTime.now();
    }
}

