package com.roommate.matching.service;

import org.springframework.stereotype.Service;

import com.roommate.matching.entity.LifestylePreferences;
import com.roommate.matching.enums.*;

@Service
public class CompatibilityCalculator {

    public int calculateScore(
            LifestylePreferences user,
            LifestylePreferences other) {

        int score = 0;

        // Sleep Schedule
        if (matches(user.getSleepSchedule(),
                other.getSleepSchedule())) {

            score += 15;
        }

        // Food Habit (supports ANY)
        if (matchesOrFlexible(
                user.getFoodHabit(),
                other.getFoodHabit(),
                FoodHabit.ANY)) {

            score += 15;
        }

        // Cleanliness Level
        if (matches(user.getCleanlinessLevel(),
                other.getCleanlinessLevel())) {

            score += 20;
        }

        // Work Mode
        if (matches(user.getWorkMode(),
                other.getWorkMode())) {

            score += 10;
        }

        // Smoking Preference (partial compatibility supported)
        score += partialMatchScore(
                user.getSmokingPreference(),
                other.getSmokingPreference(),
                10);

        // Drinking Preference (partial compatibility supported)
        score += partialMatchScore(
                user.getDrinkingPreference(),
                other.getDrinkingPreference(),
                10);

        // Guest Frequency
        if (matches(user.getGuestFrequency(),
                other.getGuestFrequency())) {

            score += 10;
        }

        // Budget Compatibility
        if (user.getBudget() != null
                && other.getBudget() != null
                && Math.abs(user.getBudget()
                        - other.getBudget()) <= 3000) {

            score += 10;
        }

        return score;
    }

    /*
     * EXACT MATCH CHECK
     */
    private <T> boolean matches(T a, T b) {

        return a != null
                && b != null
                && a.equals(b);
    }

    /*
     * MATCH OR FLEXIBLE VALUE CHECK (Example: ANY)
     */
    private <T> boolean matchesOrFlexible(
            T a,
            T b,
            T flexibleValue) {

        return a != null
                && b != null
                && (a.equals(b)
                        || a.equals(flexibleValue)
                        || b.equals(flexibleValue));
    }

    /*
     * PARTIAL MATCH SCORING
     * Example:
     * NO vs OCCASIONALLY = partial match
     */
    private <T> int partialMatchScore(
            T a,
            T b,
            int maxScore) {

        if (a == null || b == null) {
            return 0;
        }

        if (a.equals(b)) {
            return maxScore;
        }

        if (a.toString().equals("OCCASIONALLY")
                || b.toString().equals("OCCASIONALLY")) {

            return maxScore / 2;
        }

        return 0;
    }
}