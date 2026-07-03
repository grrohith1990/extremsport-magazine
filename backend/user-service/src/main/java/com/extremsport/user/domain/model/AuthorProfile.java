package com.extremsport.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Domain Entity: AuthorProfile
 * Extended profile for users with the AUTHOR role.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorProfile {

    private UUID id;
    private UUID userId;
    private String penName;
    private String biography;
    private String specialization;
    private List<String> expertise;
    private String profileImageUrl;
    private String socialMediaLinks;
    private int articleCount;
    private boolean verified;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

