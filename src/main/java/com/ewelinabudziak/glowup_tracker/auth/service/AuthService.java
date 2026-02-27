package com.ewelinabudziak.glowup_tracker.auth.service;

import com.ewelinabudziak.glowup_tracker.auth.dto.LoginRequest;
import com.ewelinabudziak.glowup_tracker.auth.dto.RegisterRequest;
import com.ewelinabudziak.glowup_tracker.exception.ConflictException;
import com.ewelinabudziak.glowup_tracker.user.entity.User;
import com.ewelinabudziak.glowup_tracker.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.email())) {
            throw new ConflictException("Email already in use");
        }

        User user = new User(registerRequest.email(), passwordEncoder.encode(registerRequest.password()));
        userRepository.save(user);
    }

    public void login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new ConflictException("Invalid credentials"));

        if(!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new ConflictException("Invalid credentials");
        }
    }
}
