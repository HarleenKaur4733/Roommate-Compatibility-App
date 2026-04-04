package com.roommate.matching.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roommate.matching.entity.GroupMembership;
import com.roommate.matching.entity.RoommateGroup;
import com.roommate.matching.entity.User;

@Repository
public interface GroupMembershipRepository
        extends JpaRepository<GroupMembership, Long> {

    Optional<GroupMembership> findByUser(User user);

    List<GroupMembership> findByGroup(RoommateGroup group);
}