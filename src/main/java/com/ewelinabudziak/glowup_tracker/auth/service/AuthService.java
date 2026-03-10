package com.ewelinabudziak.glowup_tracker.auth.service;

import com.ewelinabudziak.glowup_tracker.auth.dto.AuthResponse;
import com.ewelinabudziak.glowup_tracker.auth.dto.LoginRequest;
import com.ewelinabudziak.glowup_tracker.auth.dto.RegisterRequest;
import com.ewelinabudziak.glowup_tracker.exception.ConflictException;
import com.ewelinabudziak.glowup_tracker.security.jwt.JwtService;
import com.ewelinabudziak.glowup_tracker.user.entity.User;
import com.ewelinabudziak.glowup_tracker.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new ConflictException("Email already in use");
        }

        String hashedPassword = passwordEncoder.encode(registerRequest.password());

        User user = new User(registerRequest.email(), hashedPassword);
        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );

        String token = jwtService.generateToken(loginRequest.email());
        return new AuthResponse(token);
    }
}
