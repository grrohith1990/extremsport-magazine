package com.extremsport.user.adapter.in.web;

import com.extremsport.user.adapter.in.web.dto.AuthorProfileResponse;
import com.extremsport.user.adapter.in.web.dto.CreateAuthorRequest;
import com.extremsport.user.adapter.in.web.dto.UpdateAuthorRequest;
import com.extremsport.user.domain.port.in.AuthorUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authors", description = "Author profile management")
public class AuthorController {

    private final AuthorUseCase authorUseCase;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create author profile", description = "Create a new author profile (requires ADMIN or EDITOR role)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Author profile created"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public AuthorProfileResponse createAuthorProfile(@Valid @RequestBody CreateAuthorRequest request) {
        return AuthorProfileResponse.from(authorUseCase.createAuthorProfile(new AuthorUseCase.CreateAuthorProfileCommand(
                request.userId(),
                request.penName(),
                request.biography(),
                request.specialization(),
                request.expertise(),
                request.profileImageUrl()
        )));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get author profile", description = "Retrieve an author profile by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Author found"),
            @ApiResponse(responseCode = "404", description = "Author not found")
    })
    public ResponseEntity<AuthorProfileResponse> getAuthorProfile(@Parameter(description = "Author UUID") @PathVariable UUID id) {
        return authorUseCase.getAuthorProfileById(id)
                .map(author -> ResponseEntity.ok(AuthorProfileResponse.from(author)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get author by user ID", description = "Retrieve author profile by associated user ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Author found"),
            @ApiResponse(responseCode = "404", description = "Author not found")
    })
    public ResponseEntity<AuthorProfileResponse> getAuthorByUserId(@Parameter(description = "User UUID") @PathVariable UUID userId) {
        return authorUseCase.getAuthorProfileByUserId(userId)
                .map(author -> ResponseEntity.ok(AuthorProfileResponse.from(author)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all authors", description = "Retrieve paginated list of all authors")
    public List<AuthorProfileResponse> getAllAuthors(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size) {
        return AuthorProfileResponse.from(authorUseCase.getAllAuthors(page, size));
    }

    @GetMapping("/verified")
    @Operation(summary = "Get verified authors", description = "Retrieve all verified author profiles")
    public List<AuthorProfileResponse> getVerifiedAuthors() {
        return AuthorProfileResponse.from(authorUseCase.getVerifiedAuthors());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR') or hasRole('ADMIN')")
    @Operation(summary = "Update author profile", description = "Update an author profile (requires AUTHOR or ADMIN role)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Author profile updated"),
            @ApiResponse(responseCode = "404", description = "Author not found")
    })
    public AuthorProfileResponse updateAuthorProfile(@Parameter(description = "Author UUID") @PathVariable UUID id, @Valid @RequestBody UpdateAuthorRequest request) {
        return AuthorProfileResponse.from(authorUseCase.updateAuthorProfile(id, new AuthorUseCase.UpdateAuthorProfileCommand(
                request.penName(),
                request.biography(),
                request.specialization(),
                request.expertise(),
                request.profileImageUrl(),
                request.socialMediaLinks()
        )));
    }

    @PostMapping("/{id}/verify")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Verify author", description = "Verify an author profile (requires ADMIN role)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Author verified"),
            @ApiResponse(responseCode = "404", description = "Author not found")
    })
    public ResponseEntity<Void> verifyAuthor(@Parameter(description = "Author UUID") @PathVariable UUID id) {
        authorUseCase.verifyAuthor(id);
        return ResponseEntity.ok().build();
    }
}
