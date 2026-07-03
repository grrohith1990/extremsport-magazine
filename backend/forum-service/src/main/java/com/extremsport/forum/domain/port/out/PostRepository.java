package com.extremsport.forum.domain.port.out;

import com.extremsport.forum.domain.model.ForumPost;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository {

    ForumPost save(ForumPost post);

    Optional<ForumPost> findById(UUID id);

    List<ForumPost> findByThreadId(UUID threadId, int page, int size);

    List<ForumPost> findByStatus(ForumPost.PostStatus status, int page, int size);

    List<ForumPost> findByAuthorId(UUID authorId);

    void deleteById(UUID id);
}

