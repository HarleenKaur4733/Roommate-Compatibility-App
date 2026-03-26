package com.roommate.matching.dto;

import com.roommate.matching.enums.CleanlinessLevel;
import com.roommate.matching.enums.DrinkingPreference;
import com.roommate.matching.enums.FoodHabit;
import com.roommate.matching.enums.GuestFrequency;
import com.roommate.matching.enums.SleepSchedule;
import com.roommate.matching.enums.SmokingPreference;
import com.roommate.matching.enums.WorkMode;

import lombok.Data;

@Data
public class LifestylePreferencesRequest {

    private SleepSchedule sleepSchedule;

    private FoodHabit foodHabit;

    private CleanlinessLevel cleanlinessLevel;

    private WorkMode workMode;

    private SmokingPreference smokingPreference;

    private DrinkingPreference drinkingPreference;

    private GuestFrequency guestFrequency;

    private Integer budget;
}