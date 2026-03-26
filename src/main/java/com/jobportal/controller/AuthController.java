package com.jobportal.controller;

import com.jobportal.dto.LoginRequest;
import com.jobportal.dto.LoginResponse;
import com.jobportal.dto.SignupRequest;
import com.jobportal.dto.SignupResponse;
import com.jobportal.entity.User;
import com.jobportal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/signup")
    public SignupResponse registerUser(@RequestBody SignupRequest request)
    {
         return userService.registerUser(request);
    }

    // login api
    @PostMapping("/login")
    public LoginResponse loginResponse(@Valid @RequestBody LoginRequest request)
    {
         return userService.loginUser(request);
    }


}
