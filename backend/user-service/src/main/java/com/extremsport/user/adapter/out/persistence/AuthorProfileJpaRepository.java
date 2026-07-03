package com.extremsport.user.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorProfileJpaRepository extends JpaRepository<AuthorProfileJpaEntity, UUID> {

    Optional<AuthorProfileJpaEntity> findByUserId(UUID userId);

    List<AuthorProfileJpaEntity> findByVerified(boolean verified);

    Page<AuthorProfileJpaEntity> findAll(Pageable pageable);
}

