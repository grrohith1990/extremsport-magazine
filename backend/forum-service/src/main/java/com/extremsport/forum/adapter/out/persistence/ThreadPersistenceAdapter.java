package com.extremsport.forum.adapter.out.persistence;

import com.extremsport.forum.domain.model.ForumThread;
import com.extremsport.forum.domain.port.out.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ThreadPersistenceAdapter implements ThreadRepository {

    private final ThreadJpaRepository jpaRepository;

    @Override
    public ForumThread save(ForumThread thread) {
        ThreadJpaEntity entity = toEntity(thread);
        ThreadJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<ForumThread> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<ForumThread> findByCategory(ForumThread.ThreadCategory category, int page, int size) {
        return jpaRepository.findByCategory(category.name(), PageRequest.of(page, size))
                .stream().map(this::toDomain).toList();
    }

    @Override
    public List<ForumThread> findRecent(int page, int size) {
        return jpaRepository.findByStatusNotOrderByPinnedDescLastPostAtDesc("DELETED", PageRequest.of(page, size))
                .stream().map(this::toDomain).toList();
    }

    @Override
    public List<ForumThread> search(String query, int page, int size) {
        return jpaRepository.search(query, PageRequest.of(page, size))
                .stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private ForumThread toDomain(ThreadJpaEntity entity) {
        return ForumThread.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .authorId(entity.getAuthorId())
                .authorName(entity.getAuthorName())
                .category(ForumThread.ThreadCategory.valueOf(entity.getCategory()))
                .status(ForumThread.ThreadStatus.valueOf(entity.getStatus()))
                .postCount(entity.getPostCount())
                .viewCount(entity.getViewCount())
                .pinned(entity.isPinned())
                .locked(entity.isLocked())
                .lastPostAt(entity.getLastPostAt())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private ThreadJpaEntity toEntity(ForumThread thread) {
        return ThreadJpaEntity.builder()
                .id(thread.getId())
                .title(thread.getTitle())
                .description(thread.getDescription())
                .authorId(thread.getAuthorId())
                .authorName(thread.getAuthorName())
                .category(thread.getCategory().name())
                .status(thread.getStatus().name())
                .postCount(thread.getPostCount())
                .viewCount(thread.getViewCount())
                .pinned(thread.isPinned())
                .locked(thread.isLocked())
                .lastPostAt(thread.getLastPostAt())
                .createdAt(thread.getCreatedAt())
                .updatedAt(thread.getUpdatedAt())
                .build();
    }
}

