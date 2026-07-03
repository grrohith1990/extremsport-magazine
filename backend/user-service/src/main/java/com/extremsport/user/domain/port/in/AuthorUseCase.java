package com.extremsport.user.domain.port.in;

import com.extremsport.user.domain.model.AuthorProfile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Primary Port: Author profile management use cases.
 */
public interface AuthorUseCase {

    AuthorProfile createAuthorProfile(CreateAuthorProfileCommand command);

    AuthorProfile updateAuthorProfile(UUID authorId, UpdateAuthorProfileCommand command);

    Optional<AuthorProfile> getAuthorProfileByUserId(UUID userId);

    Optional<AuthorProfile> getAuthorProfileById(UUID authorId);

    List<AuthorProfile> getAllAuthors(int page, int size);

    List<AuthorProfile> getVerifiedAuthors();

    void verifyAuthor(UUID authorId);

    record CreateAuthorProfileCommand(
            UUID userId,
            String penName,
            String biography,
            String specialization,
            List<String> expertise,
            String profileImageUrl
    ) {}

    record UpdateAuthorProfileCommand(
            String penName,
            String biography,
            String specialization,
            List<String> expertise,
            String profileImageUrl,
            String socialMediaLinks
    ) {}
}

