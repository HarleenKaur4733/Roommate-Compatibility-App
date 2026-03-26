package com.roommate.matching.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roommate.matching.service.MatchRequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchRequestController {

    private final MatchRequestService service;

    @PostMapping("/request/{targetUserId}")
    public ResponseEntity<?> sendRequest(@PathVariable Long targetUserId) {
        System.out.println("Received request to send match request to user ID: " + targetUserId);
        service.sendRequest(targetUserId);

        return ResponseEntity.ok("Request sent");
    }

    @PutMapping("/accept/{requestId}")
    public ResponseEntity<?> acceptRequest(@PathVariable Long requestId) {

        service.acceptRequest(requestId);

        return ResponseEntity.ok("Request accepted");
    }

    @PutMapping("/reject/{requestId}")
    public ResponseEntity<?> rejectRequest(@PathVariable Long requestId) {

        service.rejectRequest(requestId);

        return ResponseEntity.ok("Request rejected");
    }

    @GetMapping("/my-requests")
    public ResponseEntity<?> getRequests() {

        return ResponseEntity.ok(
                service.getMyRequests());
    }

    @GetMapping("/my-connections")
    public ResponseEntity<?> getConnections() {

        return ResponseEntity.ok(
                service.getMyConnections());
    }
}