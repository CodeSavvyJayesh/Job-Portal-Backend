package com.jobportal.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// this class will show what will be the response generated will show to the user on the frontend side
// this class won't/must not show password...
// this class will show the generated jwt token (v.imp)
@Getter
@Setter
@Builder
public class LoginResponse {
    private String token;
    private String email;

}
