package com.roommate.matching.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.roommate.matching.entity.RefreshToken;
import com.roommate.matching.entity.User;
import com.roommate.matching.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    public RefreshToken createRefreshToken(User user) {

        repository.deleteByUser(user);

        RefreshToken token = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusDays(7))
                .build();

        return repository.save(token);
    }

    public boolean isValid(RefreshToken token) {

        return token.getExpiryDate()
                .isAfter(LocalDateTime.now());
    }
}