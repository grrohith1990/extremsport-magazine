package com.extremsport.forum.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EditPostRequest(
        @NotBlank(message = "Content is required")
        String content,

        @NotNull(message = "Editor ID is required")
        UUID editorId
) {}

