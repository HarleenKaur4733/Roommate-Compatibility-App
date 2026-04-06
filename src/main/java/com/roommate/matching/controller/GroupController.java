package com.roommate.matching.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roommate.matching.entity.GroupMembership;
import com.roommate.matching.entity.RoommateGroup;
import com.roommate.matching.service.GroupService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    // Create group
    @PostMapping("/create")
    public ResponseEntity<String> createGroup(
            @RequestParam String groupName) {

        groupService.createGroup(groupName);

        return ResponseEntity.ok("Group created successfully");
    }

    // Invite user to group
    @PostMapping("/invite/{userId}")
    public ResponseEntity<String> inviteUser(
            @PathVariable Long userId) {

        groupService.inviteUser(userId);

        return ResponseEntity.ok("User invited successfully");
    }

    // Accept invite
    @PutMapping("/invite/accept/{inviteId}")
    public ResponseEntity<String> acceptInvite(
            @PathVariable Long inviteId) {

        groupService.acceptInvite(inviteId);

        return ResponseEntity.ok("Invite accepted successfully");
    }

    // Reject invite
    @PutMapping("/invite/reject/{inviteId}")
    public ResponseEntity<String> rejectInvite(
            @PathVariable Long inviteId) {

        groupService.rejectInvite(inviteId);

        return ResponseEntity.ok("Invite rejected successfully");
    }

    // Get my group
    @GetMapping("/my-group")
    public ResponseEntity<RoommateGroup> getMyGroup() {

        return ResponseEntity.ok(
                groupService.getMyGroup());
    }

    // Get group members
    @GetMapping("/members")
    public ResponseEntity<List<GroupMembership>> getMembers() {

        return ResponseEntity.ok(
                groupService.getGroupMembers());
    }

    // Leave group
    @DeleteMapping("/leave")
    public ResponseEntity<String> leaveGroup() {

        groupService.leaveGroup();

        return ResponseEntity.ok("Left group successfully");
    }

}
