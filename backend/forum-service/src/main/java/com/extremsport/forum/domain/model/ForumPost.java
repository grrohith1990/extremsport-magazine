package com.extremsport.forum.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain Entity: ForumPost
 * Represents a single post within a thread.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForumPost {

    private UUID id;
    private UUID threadId;
    private UUID authorId;
    private String authorName;
    private String content;
    private PostStatus status;
    private UUID parentPostId; // for reply threading
    private int likeCount;
    private boolean edited;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum PostStatus {
        VISIBLE,
        HIDDEN,       // Hidden by moderator
        FLAGGED,      // Flagged for review
        DELETED       // Soft deleted
    }

    public void moderate() {
        this.status = PostStatus.HIDDEN;
        this.updatedAt = LocalDateTime.now();
    }

    public void flag() {
        this.status = PostStatus.FLAGGED;
        this.updatedAt = LocalDateTime.now();
    }

    public void approve() {
        this.status = PostStatus.VISIBLE;
        this.updatedAt = LocalDateTime.now();
    }

    public void edit(String newContent) {
        this.content = newContent;
        this.edited = true;
        this.updatedAt = LocalDateTime.now();
    }
}

