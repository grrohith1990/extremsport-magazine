package com.extremsport.forum.adapter.in.web.dto;

import com.extremsport.forum.domain.model.ForumPost;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PostResponse(
        UUID id,
        UUID threadId,
        UUID authorId,
        String authorName,
        String content,
        String status,
        UUID parentPostId,
        int likeCount,
        boolean edited,
        LocalDateTime createdAt
) {
    public static PostResponse from(ForumPost post) {
        return new PostResponse(
                post.getId(),
                post.getThreadId(),
                post.getAuthorId(),
                post.getAuthorName(),
                post.getContent(),
                post.getStatus() != null ? post.getStatus().name() : null,
                post.getParentPostId(),
                post.getLikeCount(),
                post.isEdited(),
                post.getCreatedAt()
        );
    }

    public static List<PostResponse> from(List<ForumPost> posts) {
        return posts.stream().map(PostResponse::from).toList();
    }
}

