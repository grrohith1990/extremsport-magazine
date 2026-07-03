package com.extremsport.user.domain.port.out;

import com.extremsport.user.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Secondary Port: User persistence abstraction.
 */
public interface UserRepository {

    User save(User user);

    Optional<User> findById(UUID id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findAll(int page, int size);

    List<User> findByRole(User.UserRole role, int page, int size);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void deleteById(UUID id);
}

