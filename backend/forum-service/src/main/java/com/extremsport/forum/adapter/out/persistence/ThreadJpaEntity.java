package com.extremsport.forum.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "forum_threads")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThreadJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private UUID authorId;

    private String authorName;

    private String category;

    private String status;

    private int postCount;
    private int viewCount;
    private boolean pinned;
    private boolean locked;
    private LocalDateTime lastPostAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


