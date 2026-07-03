package com.extremsport.forum.application.service;

import com.extremsport.forum.domain.model.ForumPost;
import com.extremsport.forum.domain.model.ForumThread;
import com.extremsport.forum.domain.port.in.ForumUseCase;
import com.extremsport.forum.domain.port.out.ForumEventPublisher;
import com.extremsport.forum.domain.port.out.PostRepository;
import com.extremsport.forum.domain.port.out.ThreadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ForumService implements ForumUseCase {

    private final ThreadRepository threadRepository;
    private final PostRepository postRepository;
    private final ForumEventPublisher eventPublisher;

    // === Thread Operations ===

    @Override
    public ForumThread createThread(CreateThreadCommand command) {
        ForumThread thread = ForumThread.builder()
                .id(UUID.randomUUID())
                .title(command.title())
                .description(command.description())
                .authorId(command.authorId())
                .authorName(command.authorName())
                .category(command.category())
                .status(ForumThread.ThreadStatus.ACTIVE)
                .postCount(0)
                .viewCount(0)
                .pinned(false)
                .locked(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        ForumThread saved = threadRepository.save(thread);
        eventPublisher.publishThreadCreated(saved);
        log.info("Thread created: {} by user {}", saved.getId(), saved.getAuthorId());
        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ForumThread> getThreadById(UUID threadId) {
        return threadRepository.findById(threadId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ForumThread> getThreadsByCategory(ForumThread.ThreadCategory category, int page, int size) {
        return threadRepository.findByCategory(category, page, size);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ForumThread> getRecentThreads(int page, int size) {
        return threadRepository.findRecent(page, size);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ForumThread> searchThreads(String query, int page, int size) {
        return threadRepository.search(query, page, size);
    }

    // === Post Operations ===

    @Override
    public ForumPost createPost(CreatePostCommand command) {
        ForumThread thread = threadRepository.findById(command.threadId())
                .orElseThrow(() -> new ThreadNotFoundException(command.threadId()));

        if (!thread.canPost()) {
            throw new IllegalStateException("Thread is locked or closed. Cannot create new posts.");
        }

        ForumPost post = ForumPost.builder()
                .id(UUID.randomUUID())
                .threadId(command.threadId())
                .authorId(command.authorId())
                .authorName(command.authorName())
                .content(command.content())
                .parentPostId(command.parentPostId())
                .status(ForumPost.PostStatus.VISIBLE)
                .likeCount(0)
                .edited(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        ForumPost saved = postRepository.save(post);

        // Update thread metadata
        thread.incrementPostCount();
        threadRepository.save(thread);

        eventPublisher.publishPostCreated(saved);
        log.info("Post created in thread {}: {}", command.threadId(), saved.getId());
        return saved;
    }

    @Override
    public ForumPost editPost(UUID postId, String newContent, UUID editorId) {
        ForumPost post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));

        if (!post.getAuthorId().equals(editorId)) {
            throw new IllegalStateException("Only the author can edit their own posts.");
        }

        post.edit(newContent);
        return postRepository.save(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ForumPost> getPostsByThread(UUID threadId, int page, int size) {
        return postRepository.findByThreadId(threadId, page, size);
    }

    // === Moderation Operations ===

    @Override
    public void lockThread(UUID threadId) {
        ForumThread thread = threadRepository.findById(threadId)
                .orElseThrow(() -> new ThreadNotFoundException(threadId));
        thread.lock();
        threadRepository.save(thread);
        log.info("Thread locked: {}", threadId);
    }

    @Override
    public void unlockThread(UUID threadId) {
        ForumThread thread = threadRepository.findById(threadId)
                .orElseThrow(() -> new ThreadNotFoundException(threadId));
        thread.unlock();
        threadRepository.save(thread);
        log.info("Thread unlocked: {}", threadId);
    }

    @Override
    public void pinThread(UUID threadId) {
        ForumThread thread = threadRepository.findById(threadId)
                .orElseThrow(() -> new ThreadNotFoundException(threadId));
        thread.pin();
        threadRepository.save(thread);
    }

    @Override
    public void unpinThread(UUID threadId) {
        ForumThread thread = threadRepository.findById(threadId)
                .orElseThrow(() -> new ThreadNotFoundException(threadId));
        thread.unpin();
        threadRepository.save(thread);
    }

    @Override
    public void deleteThread(UUID threadId) {
        ForumThread thread = threadRepository.findById(threadId)
                .orElseThrow(() -> new ThreadNotFoundException(threadId));
        thread.setStatus(ForumThread.ThreadStatus.DELETED);
        threadRepository.save(thread);
        log.info("Thread deleted (soft): {}", threadId);
    }

    @Override
    public void moderatePost(UUID postId) {
        ForumPost post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        post.moderate();
        postRepository.save(post);
        eventPublisher.publishPostModerated(post);
        log.info("Post moderated (hidden): {}", postId);
    }

    @Override
    public void flagPost(UUID postId, UUID reporterId, String reason) {
        ForumPost post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        post.flag();
        postRepository.save(post);
        eventPublisher.publishPostFlagged(post, reason);
        log.info("Post flagged: {} by user {} - reason: {}", postId, reporterId, reason);
    }

    @Override
    public void approvePost(UUID postId) {
        ForumPost post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        post.approve();
        postRepository.save(post);
        log.info("Post approved: {}", postId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ForumPost> getFlaggedPosts(int page, int size) {
        return postRepository.findByStatus(ForumPost.PostStatus.FLAGGED, page, size);
    }
}

