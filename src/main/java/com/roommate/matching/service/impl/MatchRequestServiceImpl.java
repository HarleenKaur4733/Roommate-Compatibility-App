package com.roommate.matching.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.roommate.matching.entity.MatchRequest;
import com.roommate.matching.entity.User;
import com.roommate.matching.enums.MatchStatus;
import com.roommate.matching.repository.MatchRequestRepository;
import com.roommate.matching.repository.UserRepository;
import com.roommate.matching.service.MatchRequestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchRequestServiceImpl
                implements MatchRequestService {

        private final MatchRequestRepository repository;

        private final UserRepository userRepository;

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
        public void sendRequest(Long targetUserId) {

                User sender = getLoggedInUser();
                System.out.println("Got logged in user: " + sender.getEmail());

                User receiver = userRepository.findById(targetUserId)
                                .orElseThrow();
                System.out.println("Found target user: " + receiver.getEmail());

                boolean alreadyExists = repository.findBySenderAndReceiver(sender, receiver)
                                .filter(req -> req.getStatus() != MatchStatus.REJECTED)
                                .isPresent()

                                ||

                                repository.findBySenderAndReceiver(receiver, sender)
                                                .filter(req -> req.getStatus() != MatchStatus.REJECTED)
                                                .isPresent();

                if (alreadyExists) {

                        throw new RuntimeException("Active match request already exists between users");
                }

                if (sender.getId().equals(receiver.getId())) {

                        throw new RuntimeException("Cannot send request to yourself");
                }

                MatchRequest request = MatchRequest.builder()
                                .sender(sender)
                                .receiver(receiver)
                                .status(MatchStatus.PENDING)
                                .createdAt(LocalDateTime.now())
                                .build();
                System.out.println("Sending request to user ID: " + targetUserId);
                repository.save(request);
        }

        @Override
        public void acceptRequest(Long requestId) {

                MatchRequest request = repository.findById(requestId)
                                .orElseThrow();

                if (request.getStatus() != MatchStatus.PENDING) {

                        throw new RuntimeException("Request already processed");
                }
                request.setStatus(MatchStatus.ACCEPTED);

                repository.save(request);
        }

        @Override
        public void rejectRequest(Long requestId) {

                MatchRequest request = repository.findById(requestId)
                                .orElseThrow();
                if (request.getStatus() != MatchStatus.PENDING) {

                        throw new RuntimeException("Request already processed");
                }
                request.setStatus(MatchStatus.REJECTED);

                repository.save(request);
        }

        @Override
        public List<MatchRequest> getMyRequests() {

                User receiver = getLoggedInUser();

                return repository.findByReceiver(receiver);
        }

        @Override
        public List<MatchRequest> getMyConnections() {

                User user = getLoggedInUser();

                return repository.findAll()
                                .stream()
                                .filter(req -> req.getStatus() == MatchStatus.ACCEPTED
                                                &&
                                                (req.getSender().equals(user)
                                                                ||
                                                                req.getReceiver().equals(user)))
                                .toList();
        }

        @Override
        public List<MatchRequest> getRequestsSent() {

                User user = getLoggedInUser();

                return repository.findAll()
                                .stream()
                                .filter(req -> req.getStatus() == MatchStatus.PENDING
                                                &&
                                                (req.getSender().equals(user)))
                                .toList();
        }
}