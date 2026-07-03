package com.extremsport.user.application.service;

import com.extremsport.user.domain.model.User;
import com.extremsport.user.domain.port.in.UserUseCase;
import com.extremsport.user.domain.port.out.AuthenticationPort;
import com.extremsport.user.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserUseCase {

    private final UserRepository userRepository;
    private final AuthenticationPort authenticationPort;

    @Override
    public User registerUser(RegisterUserCommand command) {
        if (userRepository.existsByUsername(command.username())) {
            throw new IllegalArgumentException("Username already exists: " + command.username());
        }
        if (userRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("Email already exists: " + command.email());
        }

        Set<User.UserRole> roles = command.roles() != null ? command.roles() : new HashSet<>(Set.of(User.UserRole.READER));

        User user = User.builder()
                .id(UUID.randomUUID())
                .username(command.username())
                .email(command.email())
                .firstName(command.firstName())
                .lastName(command.lastName())
                .displayName(command.displayName() != null ? command.displayName() : command.firstName())
                .roles(roles)
                .active(true)
                .emailVerified(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        User saved = userRepository.save(user);

        // Sync to auth provider (graceful degradation)
        if (authenticationPort.isAvailable()) {
            authenticationPort.syncUserToAuthProvider(saved);
        }

        log.info("User registered: {} ({})", saved.getUsername(), saved.getId());
        return saved;
    }

    @Override
    public User updateProfile(UUID userId, UpdateProfileCommand command) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());
        user.setDisplayName(command.displayName());
        user.setAvatarUrl(command.avatarUrl());
        user.setBio(command.bio());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers(int page, int size) {
        return userRepository.findAll(page, size);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsersByRole(User.UserRole role, int page, int size) {
        return userRepository.findByRole(role, page, size);
    }

    @Override
    public void grantRole(UUID userId, User.UserRole role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.grantRole(role);
        userRepository.save(user);

        if (authenticationPort.isAvailable()) {
            authenticationPort.assignRoleInAuthProvider(userId.toString(), role.name());
        }
        log.info("Role {} granted to user {}", role, userId);
    }

    @Override
    public void revokeRole(UUID userId, User.UserRole role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.revokeRole(role);
        userRepository.save(user);

        if (authenticationPort.isAvailable()) {
            authenticationPort.revokeRoleInAuthProvider(userId.toString(), role.name());
        }
        log.info("Role {} revoked from user {}", role, userId);
    }

    @Override
    public void deactivateUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.setActive(false);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        log.info("User deactivated: {}", userId);
    }

    @Override
    public void activateUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.setActive(true);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        log.info("User activated: {}", userId);
    }

    @Override
    public void updateLastLogin(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);
    }
}

