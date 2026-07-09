package com.extremsport.user.adapter.in.web.dto;

import com.extremsport.user.domain.model.AuthorProfile;

import java.util.List;
import java.util.UUID;

public record AuthorProfileResponse(
        UUID id,
        UUID userId,
        String penName,
        String biography,
        String specialization,
        List<String> expertise,
        String profileImageUrl,
        String socialMediaLinks,
        int articleCount,
        boolean verified
) {
    public static AuthorProfileResponse from(AuthorProfile author) {
        return new AuthorProfileResponse(
                author.getId(),
                author.getUserId(),
                author.getPenName(),
                author.getBiography(),
                author.getSpecialization(),
                author.getExpertise(),
                author.getProfileImageUrl(),
                author.getSocialMediaLinks(),
                author.getArticleCount(),
                author.isVerified()
        );
    }

    public static List<AuthorProfileResponse> from(List<AuthorProfile> authors) {
        return authors.stream().map(AuthorProfileResponse::from).toList();
    }
}

