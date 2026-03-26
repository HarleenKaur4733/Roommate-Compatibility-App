package com.roommate.matching.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roommate.matching.service.MatchSuggestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchSuggestionController {

    private final MatchSuggestionService service;

    @GetMapping("/suggestions")
    public ResponseEntity<?> getSuggestions() {

        return ResponseEntity.ok(
                service.getSuggestions());
    }
}