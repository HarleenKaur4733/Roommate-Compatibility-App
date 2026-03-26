package com.roommate.matching.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.roommate.matching.dto.LifestylePreferencesRequest;
import com.roommate.matching.entity.LifestylePreferences;
import com.roommate.matching.entity.User;
import com.roommate.matching.repository.LifestylePreferencesRepository;
import com.roommate.matching.repository.UserRepository;
import com.roommate.matching.service.LifestylePreferencesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LifestylePreferencesServiceImpl
                implements LifestylePreferencesService {

        private final LifestylePreferencesRepository repository;

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
        public LifestylePreferences createPreferences(
                        LifestylePreferencesRequest request) {

                User user = getLoggedInUser();

                if (repository.findByUser(user).isPresent()) {

                        throw new RuntimeException(
                                        "Preferences already exist");
                }

                LifestylePreferences preferences = LifestylePreferences.builder()
                                .user(user)
                                .sleepSchedule(request.getSleepSchedule())
                                .foodHabit(request.getFoodHabit())
                                .cleanlinessLevel(request.getCleanlinessLevel())
                                .workMode(request.getWorkMode())
                                .smokingPreference(request.getSmokingPreference())
                                .drinkingPreference(request.getDrinkingPreference())
                                .guestFrequency(request.getGuestFrequency())
                                .budget(request.getBudget())
                                .build();

                return repository.save(preferences);
        }

        @Override
        public LifestylePreferences updatePreferences(
                        LifestylePreferencesRequest request) {

                User user = getLoggedInUser();

                LifestylePreferences preferences = repository.findByUser(user)
                                .orElseThrow();

                if (request.getSleepSchedule() != null) {
                        preferences.setSleepSchedule(request.getSleepSchedule());
                }

                if (request.getFoodHabit() != null) {
                        preferences.setFoodHabit(request.getFoodHabit());
                }

                if (request.getCleanlinessLevel() != null) {
                        preferences.setCleanlinessLevel(request.getCleanlinessLevel());
                }

                if (request.getWorkMode() != null) {
                        preferences.setWorkMode(request.getWorkMode());
                }

                if (request.getSmokingPreference() != null) {
                        preferences.setSmokingPreference(request.getSmokingPreference());
                }

                if (request.getDrinkingPreference() != null) {
                        preferences.setDrinkingPreference(request.getDrinkingPreference());
                }

                if (request.getGuestFrequency() != null) {
                        preferences.setGuestFrequency(request.getGuestFrequency());
                }

                if (request.getBudget() != null) {
                        preferences.setBudget(request.getBudget());
                }

                return repository.save(preferences);
        }

        @Override
        public LifestylePreferences getMyPreferences() {

                User user = getLoggedInUser();

                return repository.findByUser(user)
                                .orElseThrow();
        }
}