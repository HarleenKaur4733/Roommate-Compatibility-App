package com.roommate.matching.service;

import java.util.List;

import com.roommate.matching.entity.MatchRequest;

public interface MatchRequestService {

    void sendRequest(Long targetUserId);

    void acceptRequest(Long requestId);

    void rejectRequest(Long requestId);

    List<MatchRequest> getMyRequests();

    List<MatchRequest> getMyConnections();

    List<MatchRequest> getRequestsSent();

}