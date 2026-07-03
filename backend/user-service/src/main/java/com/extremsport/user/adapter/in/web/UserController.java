package com.extremsport.user.adapter.in.web;

import com.extremsport.user.domain.model.User;
import com.extremsport.user.domain.port.in.UserUseCase;
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
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        return userUseCase.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return userUseCase.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return userUseCase.getAllUsers(page, size);
    }

    @GetMapping("/role/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getUsersByRole(
            @PathVariable User.UserRole role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return userUseCase.getUsersByRole(role, page, size);
    }

    @PutMapping("/{id}/profile")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public User updateProfile(@PathVariable UUID id, @Valid @RequestBody UpdateProfileRequest request) {
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
    public ResponseEntity<Void> grantRole(@PathVariable UUID id, @PathVariable User.UserRole role) {
        userUseCase.grantRole(id, role);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/roles/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> revokeRole(@PathVariable UUID id, @PathVariable User.UserRole role) {
        userUseCase.revokeRole(id, role);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deactivateUser(@PathVariable UUID id) {
        userUseCase.deactivateUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> activateUser(@PathVariable UUID id) {
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

