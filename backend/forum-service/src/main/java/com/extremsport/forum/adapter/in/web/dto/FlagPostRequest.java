package com.extremsport.forum.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FlagPostRequest(
        @NotNull(message = "Reporter ID is required")
        UUID reporterId,

        @NotBlank(message = "Reason is required")
        String reason
) {}

