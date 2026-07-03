package com.extremsport.forum.domain.port.in;

import com.extremsport.forum.domain.model.ForumPost;
import com.extremsport.forum.domain.model.ForumThread;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Primary Port: Forum management use cases.
 */
public interface ForumUseCase {

    // === Thread Operations ===

    ForumThread createThread(CreateThreadCommand command);

    Optional<ForumThread> getThreadById(UUID threadId);

    List<ForumThread> getThreadsByCategory(ForumThread.ThreadCategory category, int page, int size);

    List<ForumThread> getRecentThreads(int page, int size);

    List<ForumThread> searchThreads(String query, int page, int size);

    // === Post Operations ===

    ForumPost createPost(CreatePostCommand command);

    ForumPost editPost(UUID postId, String newContent, UUID editorId);

    List<ForumPost> getPostsByThread(UUID threadId, int page, int size);

    // === Moderation Operations ===

    void lockThread(UUID threadId);

    void unlockThread(UUID threadId);

    void pinThread(UUID threadId);

    void unpinThread(UUID threadId);

    void deleteThread(UUID threadId);

    void moderatePost(UUID postId);

    void flagPost(UUID postId, UUID reporterId, String reason);

    void approvePost(UUID postId);

    List<ForumPost> getFlaggedPosts(int page, int size);

    // === Commands ===

    record CreateThreadCommand(
            String title,
            String description,
            UUID authorId,
            String authorName,
            ForumThread.ThreadCategory category
    ) {}

    record CreatePostCommand(
            UUID threadId,
            UUID authorId,
            String authorName,
            String content,
            UUID parentPostId
    ) {}
}

