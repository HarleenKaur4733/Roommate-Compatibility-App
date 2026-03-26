package com.roommate.matching.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roommate.matching.entity.RefreshToken;
import com.roommate.matching.entity.User;
import com.roommate.matching.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    @Transactional
    public RefreshToken createRefreshToken(User user) {

        System.out.println("Deleting old refresh token for user: " + user.getEmail());
        repository.deleteByUser(user);

        RefreshToken token = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusDays(7))
                .build();
        System.out.println("Creating new refresh token for user: " + user.getEmail());

        return repository.save(token);
    }

    public boolean isValid(RefreshToken token) {

        return token.getExpiryDate()
                .isAfter(LocalDateTime.now());
    }
}