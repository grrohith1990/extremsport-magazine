package com.extremsport.user.adapter.out.persistence;

import com.extremsport.user.domain.model.User;
import com.extremsport.user.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public User save(User user) {
        UserJpaEntity entity = toJpaEntity(user);
        UserJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaRepository.findByUsername(username).map(this::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(this::toDomain);
    }

    @Override
    public List<User> findAll(int page, int size) {
        return jpaRepository.findAll(PageRequest.of(page, size))
                .stream().map(this::toDomain).toList();
    }

    @Override
    public List<User> findByRole(User.UserRole role, int page, int size) {
        var jpaRole = UserJpaEntity.UserRoleJpa.valueOf(role.name());
        return jpaRepository.findByRolesContaining(jpaRole, PageRequest.of(page, size))
                .stream().map(this::toDomain).toList();
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private User toDomain(UserJpaEntity entity) {
        return User.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .displayName(entity.getDisplayName())
                .avatarUrl(entity.getAvatarUrl())
                .bio(entity.getBio())
                .roles(entity.getRoles().stream()
                        .map(r -> User.UserRole.valueOf(r.name()))
                        .collect(Collectors.toSet()))
                .active(entity.isActive())
                .emailVerified(entity.isEmailVerified())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .lastLoginAt(entity.getLastLoginAt())
                .build();
    }

    private UserJpaEntity toJpaEntity(User user) {
        return UserJpaEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .displayName(user.getDisplayName())
                .avatarUrl(user.getAvatarUrl())
                .bio(user.getBio())
                .roles(user.getRoles().stream()
                        .map(r -> UserJpaEntity.UserRoleJpa.valueOf(r.name()))
                        .collect(Collectors.toSet()))
                .active(user.isActive())
                .emailVerified(user.isEmailVerified())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .lastLoginAt(user.getLastLoginAt())
                .build();
    }
}

