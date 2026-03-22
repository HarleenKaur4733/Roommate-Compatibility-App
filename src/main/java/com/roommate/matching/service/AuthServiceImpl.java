package com.roommate.matching.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.roommate.matching.dto.AuthRequest;
import com.roommate.matching.dto.AuthResponse;
import com.roommate.matching.entity.RefreshToken;
import com.roommate.matching.entity.Role;
import com.roommate.matching.entity.User;
import com.roommate.matching.repository.RefreshTokenRepository;
import com.roommate.matching.repository.UserRepository;
import com.roommate.matching.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final RefreshTokenService refreshTokenService;

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void signup(AuthRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {

            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()))
                .role(Role.USER)
                .isActive(true)
                .isVerified(true)
                .build();

        userRepository.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow();

        String accessToken = jwtUtil.generateAccessToken(
                user.getId(),
                user.getEmail());

        String refreshToken = refreshTokenService
                .createRefreshToken(user)
                .getToken();

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthResponse refreshToken(String refreshToken) {

        RefreshToken token = refreshTokenRepository
                .findByToken(refreshToken)
                .orElseThrow();

        if (!refreshTokenService.isValid(token)) {

            throw new RuntimeException("Token expired");
        }

        User user = token.getUser();

        String newAccessToken = jwtUtil.generateAccessToken(
                user.getId(),
                user.getEmail());

        return AuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .build();
    }
}