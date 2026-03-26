package com.roommate.matching.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.roommate.matching.dto.ProfileRequest;
import com.roommate.matching.entity.Profile;
import com.roommate.matching.entity.User;
import com.roommate.matching.repository.ProfileRepository;
import com.roommate.matching.repository.UserRepository;
import com.roommate.matching.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    private final UserRepository userRepository;

    private User getLoggedInUser() {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Profile createProfile(ProfileRequest request) {

        User user = getLoggedInUser();

        if (profileRepository.findByUser(user).isPresent()) {

            throw new RuntimeException(
                    "Profile already exists");
        }

        Profile profile = Profile.builder()
                .user(user)
                .name(request.getName())
                .age(request.getAge())
                .occupation(request.getOccupation())
                .city(request.getCity())
                .bio(request.getBio())
                .build();

        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(ProfileRequest request) {

        User user = getLoggedInUser();

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException(
                        "Profile not found"));

        profile.setName(request.getName());
        profile.setAge(request.getAge());
        profile.setOccupation(request.getOccupation());
        profile.setCity(request.getCity());
        profile.setBio(request.getBio());

        return profileRepository.save(profile);
    }

    @Override
    public Profile getMyProfile() {

        User user = getLoggedInUser();

        return profileRepository
                .findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    @Override
    public List<Profile> getAllProfiles() {

        return profileRepository.findAll();
    }

    @Override
    public void deleteProfile(Long id) {

        profileRepository.deleteById(id);
    }
}