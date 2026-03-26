package com.roommate.matching.service;

import com.roommate.matching.dto.LifestylePreferencesRequest;
import com.roommate.matching.entity.LifestylePreferences;

public interface LifestylePreferencesService {
    LifestylePreferences createPreferences(
            LifestylePreferencesRequest request);

    LifestylePreferences updatePreferences(
            LifestylePreferencesRequest request);

    LifestylePreferences getMyPreferences();
}
