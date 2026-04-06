package com.roommate.matching.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roommate.matching.entity.MatchRequest;
import com.roommate.matching.entity.User;
import com.roommate.matching.enums.MatchStatus;

@Repository
public interface MatchRequestRepository
                extends JpaRepository<MatchRequest, Long> {

        List<MatchRequest> findByReceiver(User receiver);

        List<MatchRequest> findBySender(User sender);

        Optional<MatchRequest> findBySenderAndReceiver(
                        User sender,
                        User receiver);

        Optional<MatchRequest> findBySenderAndReceiverAndStatus(
                        User sender,
                        User receiver,
                        MatchStatus status);
}