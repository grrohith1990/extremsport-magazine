package com.extremsport.user.domain.port.in;

import com.extremsport.user.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Primary Port: User management use cases.
 */
public interface UserUseCase {

    User registerUser(RegisterUserCommand command);

    User updateProfile(UUID userId, UpdateProfileCommand command);

    Optional<User> getUserById(UUID userId);

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);

    List<User> getAllUsers(int page, int size);

    List<User> getUsersByRole(User.UserRole role, int page, int size);

    void grantRole(UUID userId, User.UserRole role);

    void revokeRole(UUID userId, User.UserRole role);

    void deactivateUser(UUID userId);

    void activateUser(UUID userId);

    void updateLastLogin(UUID userId);

    record RegisterUserCommand(
            String username,
            String email,
            String firstName,
            String lastName,
            String displayName,
            Set<User.UserRole> roles
    ) {}

    record UpdateProfileCommand(
            String firstName,
            String lastName,
            String displayName,
            String avatarUrl,
            String bio
    ) {}
}

