package com.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {
    // basically what should be visible ?
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String location;
    private String headline;
    private String about;
    private String profileImage;
}
