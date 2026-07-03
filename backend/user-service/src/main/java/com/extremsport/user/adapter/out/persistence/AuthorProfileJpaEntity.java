package com.extremsport.user.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "author_profiles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorProfileJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    private String penName;

    @Column(length = 3000)
    private String biography;

    private String specialization;

    @ElementCollection
    @CollectionTable(name = "author_expertise", joinColumns = @JoinColumn(name = "author_id"))
    @Column(name = "expertise")
    private List<String> expertise;

    private String profileImageUrl;
    private String socialMediaLinks;
    private int articleCount;
    private boolean verified;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

