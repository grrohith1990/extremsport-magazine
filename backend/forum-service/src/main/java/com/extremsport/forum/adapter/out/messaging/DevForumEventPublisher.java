package com.extremsport.forum.adapter.out.messaging;

import com.extremsport.forum.domain.model.ForumPost;
import com.extremsport.forum.domain.model.ForumThread;
import com.extremsport.forum.domain.port.out.ForumEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("dev")
@Primary
public class DevForumEventPublisher implements ForumEventPublisher {

    @Override
    public void publishThreadCreated(ForumThread thread) {
        log.info("[DEV EVENT] Thread created: {} - {}", thread.getId(), thread.getTitle());
    }

    @Override
    public void publishPostCreated(ForumPost post) {
        log.info("[DEV EVENT] Post created: {} in thread {}", post.getId(), post.getThreadId());
    }

    @Override
    public void publishPostFlagged(ForumPost post, String reason) {
        log.info("[DEV EVENT] Post flagged: {} - reason: {}", post.getId(), reason);
    }

    @Override
    public void publishPostModerated(ForumPost post) {
        log.info("[DEV EVENT] Post moderated: {}", post.getId());
    }
}

