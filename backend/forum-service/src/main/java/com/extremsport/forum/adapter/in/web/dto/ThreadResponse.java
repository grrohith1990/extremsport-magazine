package com.extremsport.forum.adapter.in.web.dto;

import com.extremsport.forum.domain.model.ForumThread;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ThreadResponse(
        UUID id,
        String title,
        String description,
        UUID authorId,
        String authorName,
        String category,
        String status,
        int postCount,
        int viewCount,
        boolean pinned,
        boolean locked,
        LocalDateTime lastPostAt,
        LocalDateTime createdAt
) {
    public static ThreadResponse from(ForumThread thread) {
        return new ThreadResponse(
                thread.getId(),
                thread.getTitle(),
                thread.getDescription(),
                thread.getAuthorId(),
                thread.getAuthorName(),
                thread.getCategory() != null ? thread.getCategory().name() : null,
                thread.getStatus() != null ? thread.getStatus().name() : null,
                thread.getPostCount(),
                thread.getViewCount(),
                thread.isPinned(),
                thread.isLocked(),
                thread.getLastPostAt(),
                thread.getCreatedAt()
        );
    }

    public static List<ThreadResponse> from(List<ForumThread> threads) {
        return threads.stream().map(ThreadResponse::from).toList();
    }
}

