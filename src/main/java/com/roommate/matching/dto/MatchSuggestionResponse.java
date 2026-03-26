package com.roommate.matching.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchSuggestionResponse {

    private Long userId;

    private String name;

    private Integer compatibilityScore;
}