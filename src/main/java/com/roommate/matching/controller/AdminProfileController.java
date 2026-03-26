package com.roommate.matching.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roommate.matching.entity.Profile;
import com.roommate.matching.service.ProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/profile")
@RequiredArgsConstructor
public class AdminProfileController {

    private final ProfileService profileService;

    @GetMapping("/all")
    public ResponseEntity<List<Profile>> getAllProfiles() {

        return ResponseEntity.ok(
                profileService.getAllProfiles());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfile(
            @PathVariable Long id) {

        profileService.deleteProfile(id);

        return ResponseEntity.ok("Profile deleted");
    }
}