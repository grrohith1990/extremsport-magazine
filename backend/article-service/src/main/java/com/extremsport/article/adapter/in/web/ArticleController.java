package com.extremsport.article.adapter.in.web;

import com.extremsport.article.domain.model.Article;
import com.extremsport.article.domain.port.in.ArticleUseCase;
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
public class ArticleController {

    private final ArticleUseCase articleUseCase;

    // === PUBLIC ENDPOINTS ===

    @GetMapping("/public")
    public ResponseEntity<List<Article>> getPublicArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(articleUseCase.getPublicArticles(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable UUID id) {
        return articleUseCase.getArticleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Article>> searchArticles(
            @RequestParam String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(articleUseCase.searchArticles(q, page, size));
    }

    // === PREMIUM ENDPOINTS (requires subscription) ===

    @GetMapping("/premium")
    @PreAuthorize("hasRole('SUBSCRIBER') or hasRole('ADMIN')")
    public ResponseEntity<List<Article>> getPremiumArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(articleUseCase.getPremiumArticles(page, size));
    }

    // === ARCHIVE ENDPOINTS ===

    @GetMapping("/archive")
    public ResponseEntity<List<Article>> getArchivedArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(articleUseCase.getArchivedArticles(page, size));
    }

    // === AUTHOR ENDPOINTS ===

    @PostMapping
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
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
    public Article updateArticle(@PathVariable UUID id, @Valid @RequestBody UpdateArticleRequest request) {
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
    public ResponseEntity<Void> publishArticle(@PathVariable UUID id) {
        articleUseCase.publishArticle(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/archive")
    @PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> archiveArticle(@PathVariable UUID id) {
        articleUseCase.archiveArticle(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/author/{authorId}")
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Article>> getArticlesByAuthor(@PathVariable UUID authorId) {
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

