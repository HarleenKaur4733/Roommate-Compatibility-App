package com.roommate.matching.entity;

import com.roommate.matching.enums.CleanlinessLevel;
import com.roommate.matching.enums.DrinkingPreference;
import com.roommate.matching.enums.FoodHabit;
import com.roommate.matching.enums.GuestFrequency;
import com.roommate.matching.enums.SleepSchedule;
import com.roommate.matching.enums.SmokingPreference;
import com.roommate.matching.enums.WorkMode;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lifestyle_preferences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LifestylePreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    private SleepSchedule sleepSchedule;

    @Enumerated(EnumType.STRING)
    private FoodHabit foodHabit;

    @Enumerated(EnumType.STRING)
    private CleanlinessLevel cleanlinessLevel;

    @Enumerated(EnumType.STRING)
    private WorkMode workMode;

    @Enumerated(EnumType.STRING)
    private SmokingPreference smokingPreference;

    @Enumerated(EnumType.STRING)
    private DrinkingPreference drinkingPreference;

    @Enumerated(EnumType.STRING)
    private GuestFrequency guestFrequency;

    private Integer budget;
}