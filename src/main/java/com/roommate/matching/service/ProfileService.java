package com.roommate.matching.service;

import java.util.List;

import com.roommate.matching.dto.ProfileRequest;
import com.roommate.matching.entity.Profile;

public interface ProfileService {

    Profile createProfile(ProfileRequest request);

    Profile updateProfile(ProfileRequest request);

    Profile getMyProfile();

    List<Profile> getAllProfiles();

    void deleteProfile(Long id);
}