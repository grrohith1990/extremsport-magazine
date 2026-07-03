package com.extremsport.forum.domain.port.out;

import com.extremsport.forum.domain.model.ForumThread;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ThreadRepository {

    ForumThread save(ForumThread thread);

    Optional<ForumThread> findById(UUID id);

    List<ForumThread> findByCategory(ForumThread.ThreadCategory category, int page, int size);

    List<ForumThread> findRecent(int page, int size);

    List<ForumThread> search(String query, int page, int size);

    void deleteById(UUID id);
}

