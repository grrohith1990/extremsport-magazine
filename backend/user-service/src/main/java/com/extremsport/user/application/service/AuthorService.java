package com.extremsport.user.application.service;

import com.extremsport.user.domain.model.AuthorProfile;
import com.extremsport.user.domain.model.User;
import com.extremsport.user.domain.port.in.AuthorUseCase;
import com.extremsport.user.domain.port.out.AuthorProfileRepository;
import com.extremsport.user.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthorService implements AuthorUseCase {

    private final AuthorProfileRepository authorProfileRepository;
    private final UserRepository userRepository;

    @Override
    public AuthorProfile createAuthorProfile(CreateAuthorProfileCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new UserNotFoundException(command.userId()));

        // Grant AUTHOR role if not already present
        if (!user.isAuthor()) {
            user.grantRole(User.UserRole.AUTHOR);
            userRepository.save(user);
        }

        AuthorProfile profile = AuthorProfile.builder()
                .id(UUID.randomUUID())
                .userId(command.userId())
                .penName(command.penName())
                .biography(command.biography())
                .specialization(command.specialization())
                .expertise(command.expertise())
                .profileImageUrl(command.profileImageUrl())
                .articleCount(0)
                .verified(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        AuthorProfile saved = authorProfileRepository.save(profile);
        log.info("Author profile created for user: {}", command.userId());
        return saved;
    }

    @Override
    public AuthorProfile updateAuthorProfile(UUID authorId, UpdateAuthorProfileCommand command) {
        AuthorProfile profile = authorProfileRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author profile not found: " + authorId));

        profile.setPenName(command.penName());
        profile.setBiography(command.biography());
        profile.setSpecialization(command.specialization());
        profile.setExpertise(command.expertise());
        profile.setProfileImageUrl(command.profileImageUrl());
        profile.setSocialMediaLinks(command.socialMediaLinks());
        profile.setUpdatedAt(LocalDateTime.now());

        return authorProfileRepository.save(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AuthorProfile> getAuthorProfileByUserId(UUID userId) {
        return authorProfileRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AuthorProfile> getAuthorProfileById(UUID authorId) {
        return authorProfileRepository.findById(authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorProfile> getAllAuthors(int page, int size) {
        return authorProfileRepository.findAll(page, size);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorProfile> getVerifiedAuthors() {
        return authorProfileRepository.findByVerified(true);
    }

    @Override
    public void verifyAuthor(UUID authorId) {
        AuthorProfile profile = authorProfileRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author profile not found: " + authorId));
        profile.setVerified(true);
        profile.setUpdatedAt(LocalDateTime.now());
        authorProfileRepository.save(profile);
        log.info("Author verified: {}", authorId);
    }
}

