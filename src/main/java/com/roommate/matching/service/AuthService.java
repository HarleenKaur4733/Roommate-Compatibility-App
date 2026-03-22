package com.roommate.matching.service;

import com.roommate.matching.dto.AuthRequest;
import com.roommate.matching.dto.AuthResponse;

public interface AuthService {

    void signup(AuthRequest request);

    AuthResponse login(AuthRequest request);

    AuthResponse refreshToken(String refreshToken);
}