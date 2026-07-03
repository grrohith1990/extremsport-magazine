package com.extremsport.user.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJpaEntity {

    @Id
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String firstName;
    private String lastName;
    private String displayName;
    private String avatarUrl;

    @Column(length = 2000)
    private String bio;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<UserRoleJpa> roles;

    private boolean active;
    private boolean emailVerified;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

    public enum UserRoleJpa {
        READER, SUBSCRIBER, AUTHOR, EDITOR, MODERATOR, ADMIN
    }
}

