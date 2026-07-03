package com.extremsport.user.domain.port.out;

import com.extremsport.user.domain.model.AuthorProfile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Secondary Port: Author profile persistence abstraction.
 */
public interface AuthorProfileRepository {

    AuthorProfile save(AuthorProfile profile);

    Optional<AuthorProfile> findById(UUID id);

    Optional<AuthorProfile> findByUserId(UUID userId);

    List<AuthorProfile> findAll(int page, int size);

    List<AuthorProfile> findByVerified(boolean verified);

    void deleteById(UUID id);
}

