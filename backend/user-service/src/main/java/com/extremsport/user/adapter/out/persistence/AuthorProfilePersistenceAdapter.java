package com.extremsport.user.adapter.out.persistence;

import com.extremsport.user.domain.model.AuthorProfile;
import com.extremsport.user.domain.port.out.AuthorProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthorProfilePersistenceAdapter implements AuthorProfileRepository {

    private final AuthorProfileJpaRepository jpaRepository;

    @Override
    public AuthorProfile save(AuthorProfile profile) {
        AuthorProfileJpaEntity entity = toEntity(profile);
        AuthorProfileJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<AuthorProfile> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<AuthorProfile> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId).map(this::toDomain);
    }

    @Override
    public List<AuthorProfile> findAll(int page, int size) {
        return jpaRepository.findAll(PageRequest.of(page, size)).stream().map(this::toDomain).toList();
    }

    @Override
    public List<AuthorProfile> findByVerified(boolean verified) {
        return jpaRepository.findByVerified(verified).stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private AuthorProfile toDomain(AuthorProfileJpaEntity e) {
        return AuthorProfile.builder()
                .id(e.getId()).userId(e.getUserId()).penName(e.getPenName())
                .biography(e.getBiography()).specialization(e.getSpecialization())
                .expertise(e.getExpertise()).profileImageUrl(e.getProfileImageUrl())
                .socialMediaLinks(e.getSocialMediaLinks()).articleCount(e.getArticleCount())
                .verified(e.isVerified()).createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    private AuthorProfileJpaEntity toEntity(AuthorProfile p) {
        return AuthorProfileJpaEntity.builder()
                .id(p.getId()).userId(p.getUserId()).penName(p.getPenName())
                .biography(p.getBiography()).specialization(p.getSpecialization())
                .expertise(p.getExpertise()).profileImageUrl(p.getProfileImageUrl())
                .socialMediaLinks(p.getSocialMediaLinks()).articleCount(p.getArticleCount())
                .verified(p.isVerified()).createdAt(p.getCreatedAt()).updatedAt(p.getUpdatedAt())
                .build();
    }
}

