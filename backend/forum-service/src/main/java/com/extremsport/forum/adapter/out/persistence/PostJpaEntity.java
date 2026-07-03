package com.extremsport.forum.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "forum_posts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID threadId;

    @Column(nullable = false)
    private UUID authorId;

    private String authorName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String status;

    private UUID parentPostId;
    private int likeCount;
    private boolean edited;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


