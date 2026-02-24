package com.ewelinabudziak.glowup_tracker.user.repsitory;

import com.ewelinabudziak.glowup_tracker.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
