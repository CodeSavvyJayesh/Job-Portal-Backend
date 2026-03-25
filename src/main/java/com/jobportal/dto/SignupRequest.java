package com.jobportal.dto;

import lombok.*;

// this is Request Data transfer object
// we will basically request this from forntend to backend
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequest {
    // we have to enter the name,email,password
    private String name;
    private String email;
    private String password;


}
