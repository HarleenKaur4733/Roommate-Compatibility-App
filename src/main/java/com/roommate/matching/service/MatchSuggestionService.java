package com.roommate.matching.service;

import java.util.List;

import com.roommate.matching.dto.MatchSuggestionResponse;

public interface MatchSuggestionService {

    List<MatchSuggestionResponse> getSuggestions();
}
