package com.extremsport.user.adapter.in.web;

import com.extremsport.user.domain.model.AuthorProfile;
import com.extremsport.user.domain.port.in.AuthorUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorUseCase authorUseCase;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorProfile createAuthorProfile(@Valid @RequestBody CreateAuthorRequest request) {
        return authorUseCase.createAuthorProfile(new AuthorUseCase.CreateAuthorProfileCommand(
                request.userId(),
                request.penName(),
                request.biography(),
                request.specialization(),
                request.expertise(),
                request.profileImageUrl()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorProfile> getAuthorProfile(@PathVariable UUID id) {
        return authorUseCase.getAuthorProfileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<AuthorProfile> getAuthorByUserId(@PathVariable UUID userId) {
        return authorUseCase.getAuthorProfileByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<AuthorProfile> getAllAuthors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return authorUseCase.getAllAuthors(page, size);
    }

    @GetMapping("/verified")
    public List<AuthorProfile> getVerifiedAuthors() {
        return authorUseCase.getVerifiedAuthors();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMIN')")
    public AuthorProfile updateAuthorProfile(@PathVariable UUID id, @Valid @RequestBody UpdateAuthorRequest request) {
        return authorUseCase.updateAuthorProfile(id, new AuthorUseCase.UpdateAuthorProfileCommand(
                request.penName(),
                request.biography(),
                request.specialization(),
                request.expertise(),
                request.profileImageUrl(),
                request.socialMediaLinks()
        ));
    }

    @PostMapping("/{id}/verify")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> verifyAuthor(@PathVariable UUID id) {
        authorUseCase.verifyAuthor(id);
        return ResponseEntity.ok().build();
    }

    // === Request DTOs ===

    record CreateAuthorRequest(
            UUID userId,
            String penName,
            String biography,
            String specialization,
            List<String> expertise,
            String profileImageUrl
    ) {}

    record UpdateAuthorRequest(
            String penName,
            String biography,
            String specialization,
            List<String> expertise,
            String profileImageUrl,
            String socialMediaLinks
    ) {}
}

