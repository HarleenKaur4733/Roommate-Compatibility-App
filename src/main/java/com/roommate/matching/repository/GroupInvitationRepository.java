package com.roommate.matching.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roommate.matching.entity.GroupInvitation;
import com.roommate.matching.entity.User;

@Repository
public interface GroupInvitationRepository
        extends JpaRepository<GroupInvitation, Long> {

    List<GroupInvitation> findByReceiver(User receiver);
}