package com.extremsport.article.adapter.in.web.dto;

import com.extremsport.article.domain.model.Article;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Request DTO for updating an existing article.
 */
public record UpdateArticleRequest(
        @Size(max = 200, message = "Title must not exceed 200 characters")
        String title,

        @Size(max = 300, message = "Subtitle must not exceed 300 characters")
        String subtitle,

        String content,

        @Size(max = 500, message = "Summary must not exceed 500 characters")
        String summary,

        Article.AccessType accessType,

        List<String> tags,

        String category,

        String coverImageUrl
) {}

