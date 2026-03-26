package com.roommate.matching.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roommate.matching.entity.MatchRequest;
import com.roommate.matching.entity.User;

@Repository
public interface MatchRequestRepository
        extends JpaRepository<MatchRequest, Long> {

    List<MatchRequest> findByReceiver(User receiver);

    List<MatchRequest> findBySender(User sender);

    Optional<MatchRequest> findBySenderAndReceiver(
            User sender,
            User receiver);
}