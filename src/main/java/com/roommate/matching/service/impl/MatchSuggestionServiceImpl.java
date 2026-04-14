package com.roommate.matching.service.impl;

import java.util.ArrayList;
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

                                        List<String> matchingPreferences = new ArrayList<>();

                                        if (myPreferences.getSleepSchedule() == pref.getSleepSchedule()) {

                                                matchingPreferences.add(
                                                                "Sleep: " + pref.getSleepSchedule().name().replace("_",
                                                                                " "));
                                        }

                                        if (myPreferences.getFoodHabit() == pref.getFoodHabit()) {

                                                matchingPreferences.add(
                                                                "Food: " + pref.getFoodHabit().name().replace("_",
                                                                                " "));
                                        }

                                        if (myPreferences.getCleanlinessLevel() == pref.getCleanlinessLevel()) {

                                                matchingPreferences.add(
                                                                "Cleanliness: " + pref.getCleanlinessLevel().name()
                                                                                .replace("_", " "));
                                        }

                                        if (myPreferences.getWorkMode() == pref.getWorkMode()) {

                                                matchingPreferences.add(
                                                                "Work mode: " + pref.getWorkMode().name().replace("_",
                                                                                " "));
                                        }

                                        if (myPreferences.getSmokingPreference() == pref.getSmokingPreference()) {

                                                matchingPreferences.add(
                                                                "Smoking: " + pref.getSmokingPreference().name()
                                                                                .replace("_", " "));
                                        }

                                        if (myPreferences.getDrinkingPreference() == pref.getDrinkingPreference()) {

                                                matchingPreferences.add(
                                                                "Drinking: " + pref.getDrinkingPreference().name()
                                                                                .replace("_", " "));
                                        }

                                        if (myPreferences.getGuestFrequency() == pref.getGuestFrequency()) {

                                                matchingPreferences.add(
                                                                "Guests: " + pref.getGuestFrequency().name()
                                                                                .replace("_", " "));
                                        }

                                        return MatchSuggestionResponse.builder()
                                                        .userId(pref.getUser().getId())
                                                        .name(profile.getName())
                                                        .compatibilityScore(score)
                                                        .age(profile.getAge())
                                                        .city(profile.getCity())
                                                        .occupation(profile.getOccupation())
                                                        .bio(profile.getBio())
                                                        .matchingPrefernces(matchingPreferences)
                                                        .build();

                                })

                                .sorted((a, b) -> b.getCompatibilityScore()
                                                - a.getCompatibilityScore())

                                .toList();
        }
}