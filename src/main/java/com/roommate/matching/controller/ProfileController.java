package com.roommate.matching.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roommate.matching.dto.ProfileRequest;
import com.roommate.matching.entity.Profile;
import com.roommate.matching.service.ProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<Profile> createProfile(
            @RequestBody ProfileRequest request) {

        return ResponseEntity.ok(
                profileService.createProfile(request));
    }

    @PutMapping
    public ResponseEntity<Profile> updateProfile(
            @RequestBody ProfileRequest request) {

        return ResponseEntity.ok(
                profileService.updateProfile(request));
    }

    @GetMapping("/me")
    public ResponseEntity<Profile> getMyProfile() {

        return ResponseEntity.ok(
                profileService.getMyProfile());
    }
}