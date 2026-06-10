package com.jobportal.dto;

// this will be response geenrated from the backend which will be appear at the frontend

import lombok.*;
import org.hibernate.annotations.SecondaryRow;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupResponse {
    // basically what kind of responses will go from backend as soon as user hits singup button
    // it will create a id , name , email and password
    private Long id;
    private String name;
    private String email;
    private String password;
    private String message;


}
