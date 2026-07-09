package com.extremsport.forum.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreatePostRequest(
        @NotNull(message = "Author ID is required")
        UUID authorId,

        @NotBlank(message = "Author name is required")
        String authorName,

        @NotBlank(message = "Content is required")
        String content,

        UUID parentPostId
) {}

