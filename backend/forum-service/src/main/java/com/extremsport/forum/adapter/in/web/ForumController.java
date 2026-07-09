package com.extremsport.forum.adapter.in.web;

import com.extremsport.forum.adapter.in.web.dto.*;
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
    public ThreadResponse createThread(@Valid @RequestBody CreateThreadRequest request) {
        return ThreadResponse.from(forumUseCase.createThread(new ForumUseCase.CreateThreadCommand(
                request.title(),
                request.description(),
                request.authorId(),
                request.authorName(),
                request.category()
        )));
    }

    @GetMapping("/threads/{id}")
    public ResponseEntity<ThreadResponse> getThread(@PathVariable UUID id) {
        return forumUseCase.getThreadById(id)
                .map(thread -> ResponseEntity.ok(ThreadResponse.from(thread)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/threads")
    public List<ThreadResponse> getRecentThreads(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ThreadResponse.from(forumUseCase.getRecentThreads(page, size));
    }

    @GetMapping("/threads/category/{category}")
    public List<ThreadResponse> getThreadsByCategory(
            @PathVariable ForumThread.ThreadCategory category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ThreadResponse.from(forumUseCase.getThreadsByCategory(category, page, size));
    }

    @GetMapping("/threads/search")
    public List<ThreadResponse> searchThreads(
            @RequestParam String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ThreadResponse.from(forumUseCase.searchThreads(q, page, size));
    }

    // === Post Endpoints ===

    @PostMapping("/threads/{threadId}/posts")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@PathVariable UUID threadId, @Valid @RequestBody CreatePostRequest request) {
        return PostResponse.from(forumUseCase.createPost(new ForumUseCase.CreatePostCommand(
                threadId,
                request.authorId(),
                request.authorName(),
                request.content(),
                request.parentPostId()
        )));
    }

    @GetMapping("/threads/{threadId}/posts")
    public List<PostResponse> getPostsByThread(
            @PathVariable UUID threadId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return PostResponse.from(forumUseCase.getPostsByThread(threadId, page, size));
    }

    @PutMapping("/posts/{postId}")
    @PreAuthorize("isAuthenticated()")
    public PostResponse editPost(@PathVariable UUID postId, @Valid @RequestBody EditPostRequest request) {
        return PostResponse.from(forumUseCase.editPost(postId, request.content(), request.editorId()));
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
    public ResponseEntity<Void> flagPost(@PathVariable UUID id, @Valid @RequestBody FlagPostRequest request) {
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
    public List<PostResponse> getFlaggedPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return PostResponse.from(forumUseCase.getFlaggedPosts(page, size));
    }
}
