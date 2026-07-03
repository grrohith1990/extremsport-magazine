package com.extremsport.forum.adapter.out.messaging;

import com.extremsport.forum.domain.model.ForumPost;
import com.extremsport.forum.domain.model.ForumThread;
import com.extremsport.forum.domain.port.out.ForumEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("!dev")
public class RabbitMqForumEventPublisher implements ForumEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE = "forum-events";

    @Override
    public void publishThreadCreated(ForumThread thread) {
        log.info("Publishing forum.thread.created event: {}", thread.getId());
        rabbitTemplate.convertAndSend(EXCHANGE, "forum.thread.created", thread);
    }

    @Override
    public void publishPostCreated(ForumPost post) {
        log.info("Publishing forum.post.created event: {}", post.getId());
        rabbitTemplate.convertAndSend(EXCHANGE, "forum.post.created", post);
    }

    @Override
    public void publishPostFlagged(ForumPost post, String reason) {
        log.info("Publishing forum.post.flagged event: {} reason: {}", post.getId(), reason);
        rabbitTemplate.convertAndSend(EXCHANGE, "forum.post.flagged", post);
    }

    @Override
    public void publishPostModerated(ForumPost post) {
        log.info("Publishing forum.post.moderated event: {}", post.getId());
        rabbitTemplate.convertAndSend(EXCHANGE, "forum.post.moderated", post);
    }
}
