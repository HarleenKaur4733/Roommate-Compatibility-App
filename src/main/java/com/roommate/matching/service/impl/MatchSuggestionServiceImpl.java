package com.roommate.matching.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.roommate.matching.dto.MatchSuggestionResponse;
import com.roommate.matching.entity.LifestylePreferences;
import com.roommate.matching.entity.Profile;
import com.roommate.matching.entity.User;
import com.roommate.matching.repository.LifestylePreferencesRepository;
import com.roommate.matching.repository.ProfileRepository;
import com.roommate.matching.repository.UserRepository;
import com.roommate.matching.service.CompatibilityCalculator;
import com.roommate.matching.service.MatchSuggestionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchSuggestionServiceImpl
        implements MatchSuggestionService {

    private final LifestylePreferencesRepository repository;

    private final ProfileRepository profileRepository;

    private final CompatibilityCalculator calculator;

    private final UserRepository userRepository;

    private User getLoggedInUser() {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        return userRepository
                .findByEmail(email)
                .orElseThrow();
    }

    @Override
    public List<MatchSuggestionResponse> getSuggestions() {

        User currentUser = getLoggedInUser();

        LifestylePreferences myPreferences = repository.findByUser(currentUser)
                .orElseThrow();

        List<LifestylePreferences> allPreferences = repository.findAll();

        return allPreferences.stream()

                .filter(pref -> !pref.getUser()
                        .getId()
                        .equals(currentUser.getId()))

                .map(pref -> {

                    int score = calculator.calculateScore(
                            myPreferences,
                            pref);

                    Profile profile = profileRepository.findByUser(
                            pref.getUser())
                            .orElseThrow();

                    return MatchSuggestionResponse
                            .builder()
                            .userId(pref.getUser().getId())
                            .name(profile.getName())
                            .compatibilityScore(score)
                            .build();

                })

                .sorted((a, b) -> b.getCompatibilityScore()
                        - a.getCompatibilityScore())

                .toList();
    }
}