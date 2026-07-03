package com.extremsport.forum.adapter.in.web;

import com.extremsport.forum.domain.model.ForumPost;
import com.extremsport.forum.domain.model.ForumThread;
import com.extremsport.forum.domain.port.in.ForumUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/forum")
@RequiredArgsConstructor
public class ForumController {

    private final ForumUseCase forumUseCase;

    // === Thread Endpoints ===

    @PostMapping("/threads")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    public ForumThread createThread(@Valid @RequestBody CreateThreadRequest request) {
        return forumUseCase.createThread(new ForumUseCase.CreateThreadCommand(
                request.title(),
                request.description(),
                request.authorId(),
                request.authorName(),
                request.category()
        ));
    }

    @GetMapping("/threads/{id}")
    public ResponseEntity<ForumThread> getThread(@PathVariable UUID id) {
        return forumUseCase.getThreadById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/threads")
    public List<ForumThread> getRecentThreads(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return forumUseCase.getRecentThreads(page, size);
    }

    @GetMapping("/threads/category/{category}")
    public List<ForumThread> getThreadsByCategory(
            @PathVariable ForumThread.ThreadCategory category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return forumUseCase.getThreadsByCategory(category, page, size);
    }

    @GetMapping("/threads/search")
    public List<ForumThread> searchThreads(
            @RequestParam String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return forumUseCase.searchThreads(q, page, size);
    }

    // === Post Endpoints ===

    @PostMapping("/threads/{threadId}/posts")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    public ForumPost createPost(@PathVariable UUID threadId, @Valid @RequestBody CreatePostRequest request) {
        return forumUseCase.createPost(new ForumUseCase.CreatePostCommand(
                threadId,
                request.authorId(),
                request.authorName(),
                request.content(),
                request.parentPostId()
        ));
    }

    @GetMapping("/threads/{threadId}/posts")
    public List<ForumPost> getPostsByThread(
            @PathVariable UUID threadId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return forumUseCase.getPostsByThread(threadId, page, size);
    }

    @PutMapping("/posts/{postId}")
    @PreAuthorize("isAuthenticated()")
    public ForumPost editPost(@PathVariable UUID postId, @RequestBody EditPostRequest request) {
        return forumUseCase.editPost(postId, request.content(), request.editorId());
    }

    // === Moderation Endpoints ===

    @PostMapping("/threads/{id}/lock")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> lockThread(@PathVariable UUID id) {
        forumUseCase.lockThread(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/threads/{id}/unlock")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> unlockThread(@PathVariable UUID id) {
        forumUseCase.unlockThread(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/threads/{id}/pin")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> pinThread(@PathVariable UUID id) {
        forumUseCase.pinThread(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/threads/{id}/unpin")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> unpinThread(@PathVariable UUID id) {
        forumUseCase.unpinThread(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/threads/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteThread(@PathVariable UUID id) {
        forumUseCase.deleteThread(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/posts/{id}/moderate")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> moderatePost(@PathVariable UUID id) {
        forumUseCase.moderatePost(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/posts/{id}/flag")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> flagPost(@PathVariable UUID id, @RequestBody FlagPostRequest request) {
        forumUseCase.flagPost(id, request.reporterId(), request.reason());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/posts/{id}/approve")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> approvePost(@PathVariable UUID id) {
        forumUseCase.approvePost(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/moderation/flagged")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<ForumPost> getFlaggedPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return forumUseCase.getFlaggedPosts(page, size);
    }

    // === Request DTOs ===

    record CreateThreadRequest(
            String title,
            String description,
            UUID authorId,
            String authorName,
            ForumThread.ThreadCategory category
    ) {}

    record CreatePostRequest(
            UUID authorId,
            String authorName,
            String content,
            UUID parentPostId
    ) {}

    record EditPostRequest(String content, UUID editorId) {}

    record FlagPostRequest(UUID reporterId, String reason) {}
}

