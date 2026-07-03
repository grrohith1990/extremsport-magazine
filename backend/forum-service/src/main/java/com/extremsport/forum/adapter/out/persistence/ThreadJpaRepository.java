package com.extremsport.forum.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ThreadJpaRepository extends JpaRepository<ThreadJpaEntity, UUID> {

    Page<ThreadJpaEntity> findByCategory(String category, Pageable pageable);

    Page<ThreadJpaEntity> findByStatusNotOrderByPinnedDescLastPostAtDesc(String status, Pageable pageable);

    @Query("SELECT t FROM ThreadJpaEntity t WHERE " +
            "LOWER(t.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(t.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<ThreadJpaEntity> search(@Param("query") String query, Pageable pageable);
}

