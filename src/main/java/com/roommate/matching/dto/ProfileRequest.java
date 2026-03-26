package com.roommate.matching.dto;

import lombok.Data;

@Data
public class ProfileRequest {

    private String name;

    private Integer age;

    private String occupation;

    private String city;

    private String bio;
}
