package com.extremsport.article.adapter.in.web.dto;

import com.extremsport.article.domain.model.Article;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

/**
 * Request DTO for creating a new article.
 */
public record CreateArticleRequest(
        @NotBlank(message = "Title is required")
        @Size(max = 200, message = "Title must not exceed 200 characters")
        String title,

        @Size(max = 300, message = "Subtitle must not exceed 300 characters")
        String subtitle,

        @NotBlank(message = "Content is required")
        String content,

        @Size(max = 500, message = "Summary must not exceed 500 characters")
        String summary,

        @NotNull(message = "Author ID is required")
        UUID authorId,

        @NotNull(message = "Access type is required")
        Article.AccessType accessType,

        List<String> tags,

        String category,

        String coverImageUrl
) {}

