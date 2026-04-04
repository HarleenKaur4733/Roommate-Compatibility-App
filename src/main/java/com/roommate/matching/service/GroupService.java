package com.roommate.matching.service;

import java.util.List;

import com.roommate.matching.entity.GroupMembership;
import com.roommate.matching.entity.RoommateGroup;

public interface GroupService {

    void createGroup(String groupName);

    void inviteUser(Long userId);

    void acceptInvite(Long inviteId);

    void rejectInvite(Long inviteId);

    RoommateGroup getMyGroup();

    List<GroupMembership> getGroupMembers();

    void leaveGroup();
}