package com.roommate.matching.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roommate.matching.dto.LifestylePreferencesRequest;
import com.roommate.matching.service.LifestylePreferencesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/preferences")
@RequiredArgsConstructor
public class LifestylePreferencesController {

    private final LifestylePreferencesService service;

    @PostMapping
    public ResponseEntity<?> createPreferences(
            @RequestBody LifestylePreferencesRequest request) {

        return ResponseEntity.ok(
                service.createPreferences(request));
    }

    @PutMapping
    public ResponseEntity<?> updatePreferences(
            @RequestBody LifestylePreferencesRequest request) {

        return ResponseEntity.ok(
                service.updatePreferences(request));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyPreferences() {

        return ResponseEntity.ok(
                service.getMyPreferences());
    }
}