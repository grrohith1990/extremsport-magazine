package com.extremsport.forum.adapter.out.persistence;

import com.extremsport.forum.domain.model.ForumPost;
import com.extremsport.forum.domain.port.out.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PostPersistenceAdapter implements PostRepository {

    private final PostJpaRepository jpaRepository;

    @Override
    public ForumPost save(ForumPost post) {
        PostJpaEntity entity = toEntity(post);
        PostJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<ForumPost> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<ForumPost> findByThreadId(UUID threadId, int page, int size) {
        return jpaRepository.findByThreadIdOrderByCreatedAtAsc(threadId, PageRequest.of(page, size))
                .stream().map(this::toDomain).toList();
    }

    @Override
    public List<ForumPost> findByStatus(ForumPost.PostStatus status, int page, int size) {
        return jpaRepository.findByStatus(status.name(), PageRequest.of(page, size))
                .stream().map(this::toDomain).toList();
    }

    @Override
    public List<ForumPost> findByAuthorId(UUID authorId) {
        return jpaRepository.findByAuthorId(authorId).stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private ForumPost toDomain(PostJpaEntity entity) {
        return ForumPost.builder()
                .id(entity.getId())
                .threadId(entity.getThreadId())
                .authorId(entity.getAuthorId())
                .authorName(entity.getAuthorName())
                .content(entity.getContent())
                .status(ForumPost.PostStatus.valueOf(entity.getStatus()))
                .parentPostId(entity.getParentPostId())
                .likeCount(entity.getLikeCount())
                .edited(entity.isEdited())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private PostJpaEntity toEntity(ForumPost post) {
        return PostJpaEntity.builder()
                .id(post.getId())
                .threadId(post.getThreadId())
                .authorId(post.getAuthorId())
                .authorName(post.getAuthorName())
                .content(post.getContent())
                .status(post.getStatus().name())
                .parentPostId(post.getParentPostId())
                .likeCount(post.getLikeCount())
                .edited(post.isEdited())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}

