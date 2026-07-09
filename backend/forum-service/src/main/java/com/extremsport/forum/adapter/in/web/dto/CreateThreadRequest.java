package com.extremsport.forum.adapter.in.web.dto;

import com.extremsport.forum.domain.model.ForumThread;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateThreadRequest(
        @NotBlank(message = "Title is required")
        @Size(max = 200, message = "Title must not exceed 200 characters")
        String title,

        @Size(max = 2000, message = "Description must not exceed 2000 characters")
        String description,

        @NotNull(message = "Author ID is required")
        UUID authorId,

        @NotBlank(message = "Author name is required")
        String authorName,

        @NotNull(message = "Category is required")
        ForumThread.ThreadCategory category
) {}

