package com.extremsport.forum.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostJpaRepository extends JpaRepository<PostJpaEntity, UUID> {

    Page<PostJpaEntity> findByThreadIdOrderByCreatedAtAsc(UUID threadId, Pageable pageable);

    Page<PostJpaEntity> findByStatus(String status, Pageable pageable);

    List<PostJpaEntity> findByAuthorId(UUID authorId);
}

