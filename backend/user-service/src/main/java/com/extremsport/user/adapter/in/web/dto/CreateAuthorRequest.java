package com.extremsport.user.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateAuthorRequest(
        @NotNull(message = "User ID is required")
        UUID userId,

        @NotBlank(message = "Pen name is required")
        String penName,

        String biography,
        String specialization,
        List<String> expertise,
        String profileImageUrl
) {}

