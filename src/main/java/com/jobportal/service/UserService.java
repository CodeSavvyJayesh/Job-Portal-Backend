package com.jobportal.service;

import com.jobportal.dto.LoginRequest;
import com.jobportal.dto.LoginResponse;
import com.jobportal.dto.SignupRequest;
import com.jobportal.dto.SignupResponse;
import com.jobportal.entity.User;


public interface UserService {
     // from the service layer....  we have to write logic for singup response

    // basically signup response will be take signup request as a parameter
    // signup
    SignupResponse registerUser(SignupRequest request);

    // login
    LoginResponse loginUser(LoginRequest request);
}
