package com.extremsport.forum.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain Entity: ForumThread
 * Represents a discussion thread in the moderated forum.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForumThread {

    private UUID id;
    private String title;
    private String description;
    private UUID authorId;
    private String authorName;
    private ThreadCategory category;
    private ThreadStatus status;
    private int postCount;
    private int viewCount;
    private boolean pinned;
    private boolean locked;
    private LocalDateTime lastPostAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum ThreadCategory {
        CLIMBING,
        SURFING,
        SKYDIVING,
        MOUNTAINBIKING,
        SNOWBOARDING,
        BASE_JUMPING,
        GENERAL,
        GEAR_REVIEWS,
        EVENTS,
        OFF_TOPIC
    }

    public enum ThreadStatus {
        ACTIVE,
        CLOSED,
        MODERATED,
        DELETED
    }

    public void lock() {
        this.locked = true;
        this.updatedAt = LocalDateTime.now();
    }

    public void unlock() {
        this.locked = false;
        this.updatedAt = LocalDateTime.now();
    }

    public void pin() {
        this.pinned = true;
        this.updatedAt = LocalDateTime.now();
    }

    public void unpin() {
        this.pinned = false;
        this.updatedAt = LocalDateTime.now();
    }

    public void incrementPostCount() {
        this.postCount++;
        this.lastPostAt = LocalDateTime.now();
    }

    public boolean canPost() {
        return !locked && status == ThreadStatus.ACTIVE;
    }
}

