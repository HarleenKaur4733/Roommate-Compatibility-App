package com.roommate.matching.dto;

import java.util.List;

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

    private Integer age;

    private String city;

    private String occupation;

    private String bio;

    private Integer compatibilityScore;

    private List<String> matchingPrefernces;
}