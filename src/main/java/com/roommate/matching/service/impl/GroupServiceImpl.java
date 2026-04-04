package com.roommate.matching.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.roommate.matching.entity.GroupInvitation;
import com.roommate.matching.entity.GroupMembership;
import com.roommate.matching.entity.RoommateGroup;
import com.roommate.matching.entity.User;
import com.roommate.matching.enums.MatchStatus;
import com.roommate.matching.repository.GroupInvitationRepository;
import com.roommate.matching.repository.GroupMembershipRepository;
import com.roommate.matching.repository.RoommateGroupRepository;
import com.roommate.matching.repository.UserRepository;
import com.roommate.matching.service.GroupService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final UserRepository userRepository;
    private final GroupMembershipRepository groupMembershipRepository;
    private final RoommateGroupRepository roommateGroupRepository;
    private final GroupInvitationRepository groupInvitationRepository;

    private User getLoggedInUser() {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        return userRepository
                .findByEmail(email)
                .orElseThrow();
    }

    @Override
    public void createGroup(String groupName) {

        User user = getLoggedInUser();

        if (groupMembershipRepository.findByUser(user).isPresent()) {

            throw new RuntimeException(
                    "Already part of a group");
        }

        RoommateGroup group = RoommateGroup.builder()
                .name(groupName)
                .createdBy(user)
                .createdAt(LocalDateTime.now())
                .build();

        roommateGroupRepository.save(group);

        GroupMembership membership = GroupMembership.builder()
                .group(group)
                .user(user)
                .joinedAt(LocalDateTime.now())
                .build();

        groupMembershipRepository.save(membership);
    }

    @Override
    public void inviteUser(Long userId) {
        User sender = getLoggedInUser();

        GroupMembership senderMembership = groupMembershipRepository.findByUser(sender)
                .orElseThrow(() -> new RuntimeException("Join group first"));

        User receiver = userRepository.findById(userId)
                .orElseThrow();

        if (groupMembershipRepository.findByUser(receiver).isPresent()) {

            throw new RuntimeException(
                    "User already in group");
        }

        GroupInvitation invite = GroupInvitation.builder()
                .group(senderMembership.getGroup())
                .sender(sender)
                .receiver(receiver)
                .status(MatchStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        groupInvitationRepository.save(invite);
    }

    @Override
    public void acceptInvite(Long inviteId) {

        GroupInvitation invite = groupInvitationRepository.findById(inviteId)
                .orElseThrow();

        if (invite.getStatus() != MatchStatus.PENDING) {

            throw new RuntimeException(
                    "Invite already processed");
        }

        User user = getLoggedInUser();

        if (!invite.getReceiver().getId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "Unauthorized action");
        }

        GroupMembership membership = GroupMembership.builder()
                .group(invite.getGroup())
                .user(user)
                .joinedAt(LocalDateTime.now())
                .build();

        groupMembershipRepository.save(membership);

        invite.setStatus(MatchStatus.ACCEPTED);

        groupInvitationRepository.save(invite);
    }

    @Override
    public void rejectInvite(Long inviteId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rejectInvite'");
    }

    @Override
    public RoommateGroup getMyGroup() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMyGroup'");
    }

    @Override
    public List<GroupMembership> getGroupMembers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGroupMembers'");
    }

    @Override
    public void leaveGroup() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'leaveGroup'");
    }

}
