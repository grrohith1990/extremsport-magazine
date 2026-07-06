package com.extremsport.article.adapter.in.web;

import com.extremsport.article.domain.model.Article;
import com.extremsport.article.domain.port.in.ArticleUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Primary Adapter (Driving): REST Controller.
 * Translates HTTP requests into use case calls.
 */
@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
@Tag(name = "Articles", description = "Article management endpoints for public, premium, and archived content")
public class ArticleController {

    private final ArticleUseCase articleUseCase;

    // === PUBLIC ENDPOINTS ===

    @GetMapping("/public")
    @Operation(summary = "Get public articles", description = "Retrieve paginated list of publicly accessible articles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Articles retrieved successfully")
    })
    public ResponseEntity<List<Article>> getPublicArticles(
            @Parameter(description = "Page number (zero-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(articleUseCase.getPublicArticles(page, size));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get article by ID", description = "Retrieve a single article by its unique identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Article found"),
            @ApiResponse(responseCode = "404", description = "Article not found")
    })
    public ResponseEntity<Article> getArticle(@Parameter(description = "Article UUID") @PathVariable UUID id) {
        return articleUseCase.getArticleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    @Operation(summary = "Search articles", description = "Full-text search across article titles and content")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Search results returned")
    })
    public ResponseEntity<List<Article>> searchArticles(
            @Parameter(description = "Search query") @RequestParam String q,
            @Parameter(description = "Page number (zero-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(articleUseCase.searchArticles(q, page, size));
    }

    // === PREMIUM ENDPOINTS (requires subscription) ===

    @GetMapping("/premium")
    @PreAuthorize("hasRole('SUBSCRIBER') or hasRole('ADMIN')")
    @Operation(summary = "Get premium articles", description = "Retrieve premium articles (requires SUBSCRIBER or ADMIN role)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Premium articles retrieved"),
            @ApiResponse(responseCode = "401", description = "Not authenticated"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions")
    })
    public ResponseEntity<List<Article>> getPremiumArticles(
            @Parameter(description = "Page number (zero-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(articleUseCase.getPremiumArticles(page, size));
    }

    // === ARCHIVE ENDPOINTS ===

    @GetMapping("/archive")
    @Operation(summary = "Get archived articles", description = "Retrieve paginated list of archived articles")
    public ResponseEntity<List<Article>> getArchivedArticles(
            @Parameter(description = "Page number (zero-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(articleUseCase.getArchivedArticles(page, size));
    }

    // === AUTHOR ENDPOINTS ===

    @PostMapping
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create article", description = "Create a new article (requires AUTHOR or ADMIN role)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Article created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "401", description = "Not authenticated"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions")
    })
    public Article createArticle(@Valid @RequestBody CreateArticleRequest request) {
        return articleUseCase.createArticle(new ArticleUseCase.CreateArticleCommand(
                request.title(),
                request.subtitle(),
                request.content(),
                request.summary(),
                request.authorId(),
                request.accessType(),
                request.tags(),
                request.category(),
                request.coverImageUrl()
        ));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMIN')")
    @Operation(summary = "Update article", description = "Update an existing article (requires AUTHOR or ADMIN role)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Article updated successfully"),
            @ApiResponse(responseCode = "404", description = "Article not found"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions")
    })
    public Article updateArticle(@Parameter(description = "Article UUID") @PathVariable UUID id, @Valid @RequestBody UpdateArticleRequest request) {
        return articleUseCase.updateArticle(id, new ArticleUseCase.UpdateArticleCommand(
                request.title(),
                request.subtitle(),
                request.content(),
                request.summary(),
                request.accessType(),
                request.tags(),
                request.category(),
                request.coverImageUrl()
        ));
    }

    @PostMapping("/{id}/publish")
    @PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
    @Operation(summary = "Publish article", description = "Publish a draft article (requires EDITOR or ADMIN role)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Article published"),
            @ApiResponse(responseCode = "404", description = "Article not found"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions")
    })
    public ResponseEntity<Void> publishArticle(@Parameter(description = "Article UUID") @PathVariable UUID id) {
        articleUseCase.publishArticle(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/archive")
    @PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
    @Operation(summary = "Archive article", description = "Move article to archive (requires EDITOR or ADMIN role)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Article archived"),
            @ApiResponse(responseCode = "404", description = "Article not found"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions")
    })
    public ResponseEntity<Void> archiveArticle(@Parameter(description = "Article UUID") @PathVariable UUID id) {
        articleUseCase.archiveArticle(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/author/{authorId}")
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMIN')")
    @Operation(summary = "Get articles by author", description = "Retrieve all articles written by a specific author")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Author's articles retrieved"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions")
    })
    public ResponseEntity<List<Article>> getArticlesByAuthor(@Parameter(description = "Author UUID") @PathVariable UUID authorId) {
        return ResponseEntity.ok(articleUseCase.getArticlesByAuthor(authorId));
    }

    // === Request DTOs ===

    record CreateArticleRequest(
            String title,
            String subtitle,
            String content,
            String summary,
            UUID authorId,
            Article.AccessType accessType,
            List<String> tags,
            String category,
            String coverImageUrl
    ) {}

    record UpdateArticleRequest(
            String title,
            String subtitle,
            String content,
            String summary,
            Article.AccessType accessType,
            List<String> tags,
            String category,
            String coverImageUrl
    ) {}
}
