package com.extremsport.user.adapter.in.web;

import com.extremsport.user.domain.model.User;
import com.extremsport.user.domain.port.in.UserUseCase;
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
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "User profile management and administration")
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register user", description = "Register a new user account")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public User registerUser(@Valid @RequestBody RegisterUserRequest request) {
        return userUseCase.registerUser(new UserUseCase.RegisterUserCommand(
                request.username(),
                request.email(),
                request.firstName(),
                request.lastName(),
                request.displayName(),
                request.roles()
        ));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve a user profile by UUID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> getUser(@Parameter(description = "User UUID") @PathVariable UUID id) {
        return userUseCase.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Get user by username", description = "Retrieve a user profile by username")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> getUserByUsername(@Parameter(description = "Username") @PathVariable String username) {
        return userUseCase.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all users", description = "Retrieve paginated list of all users (requires ADMIN role)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users retrieved"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions")
    })
    public List<User> getAllUsers(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size) {
        return userUseCase.getAllUsers(page, size);
    }

    @GetMapping("/role/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get users by role", description = "Retrieve users filtered by role (requires ADMIN)")
    public List<User> getUsersByRole(
            @Parameter(description = "User role") @PathVariable User.UserRole role,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size) {
        return userUseCase.getUsersByRole(role, page, size);
    }

    @PutMapping("/{id}/profile")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    @Operation(summary = "Update user profile", description = "Update profile information (own profile or ADMIN)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profile updated"),
            @ApiResponse(responseCode = "403", description = "Insufficient permissions"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public User updateProfile(@Parameter(description = "User UUID") @PathVariable UUID id, @Valid @RequestBody UpdateProfileRequest request) {
        return userUseCase.updateProfile(id, new UserUseCase.UpdateProfileCommand(
                request.firstName(),
                request.lastName(),
                request.displayName(),
                request.avatarUrl(),
                request.bio()
        ));
    }

    @PostMapping("/{id}/roles/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Grant role", description = "Grant a role to a user (requires ADMIN)")
    public ResponseEntity<Void> grantRole(@Parameter(description = "User UUID") @PathVariable UUID id, @Parameter(description = "Role to grant") @PathVariable User.UserRole role) {
        userUseCase.grantRole(id, role);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/roles/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Revoke role", description = "Revoke a role from a user (requires ADMIN)")
    public ResponseEntity<Void> revokeRole(@Parameter(description = "User UUID") @PathVariable UUID id, @Parameter(description = "Role to revoke") @PathVariable User.UserRole role) {
        userUseCase.revokeRole(id, role);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deactivate user", description = "Deactivate a user account (requires ADMIN)")
    public ResponseEntity<Void> deactivateUser(@Parameter(description = "User UUID") @PathVariable UUID id) {
        userUseCase.deactivateUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Activate user", description = "Reactivate a user account (requires ADMIN)")
    public ResponseEntity<Void> activateUser(@Parameter(description = "User UUID") @PathVariable UUID id) {
        userUseCase.activateUser(id);
        return ResponseEntity.ok().build();
    }

    // === Request DTOs ===

    record RegisterUserRequest(
            String username,
            String email,
            String firstName,
            String lastName,
            String displayName,
            Set<User.UserRole> roles
    ) {}

    record UpdateProfileRequest(
            String firstName,
            String lastName,
            String displayName,
            String avatarUrl,
            String bio
    ) {}
}
