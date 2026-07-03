package com.extremsport.forum.domain.port.out;

import com.extremsport.forum.domain.model.ForumPost;
import com.extremsport.forum.domain.model.ForumThread;

/**
 * Secondary Port: Event publishing for forum activities.
 */
public interface ForumEventPublisher {

    void publishThreadCreated(ForumThread thread);

    void publishPostCreated(ForumPost post);

    void publishPostFlagged(ForumPost post, String reason);

    void publishPostModerated(ForumPost post);
}

