package com.extremsport.user.adapter.in.web.dto;

import com.extremsport.user.domain.model.User;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        String email,
        String firstName,
        String lastName,
        String displayName,
        String avatarUrl,
        String bio,
        Set<User.UserRole> roles,
        boolean active,
        LocalDateTime createdAt
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getDisplayName(),
                user.getAvatarUrl(),
                user.getBio(),
                user.getRoles(),
                user.isActive(),
                user.getCreatedAt()
        );
    }

    public static java.util.List<UserResponse> from(java.util.List<User> users) {
        return users.stream().map(UserResponse::from).toList();
    }
}

