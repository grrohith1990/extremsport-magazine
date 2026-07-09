package com.extremsport.user.adapter.in.web.dto;

import jakarta.validation.constraints.Size;

public record UpdateProfileRequest(
        String firstName,
        String lastName,

        @Size(max = 100, message = "Display name must not exceed 100 characters")
        String displayName,

        String avatarUrl,

        @Size(max = 1000, message = "Bio must not exceed 1000 characters")
        String bio
) {}

