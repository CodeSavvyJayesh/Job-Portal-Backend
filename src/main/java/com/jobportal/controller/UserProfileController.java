package com.jobportal.controller;

import com.jobportal.dto.UserProfileRequest;
import com.jobportal.dto.UserProfileResponse;
import com.jobportal.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    // Create Profile
    @PostMapping
    public UserProfileResponse createProfile(
            @RequestBody UserProfileRequest request) {

        return userProfileService.createProfile(request);
    }

    // Get Logged In User Profile
    @GetMapping
    public UserProfileResponse getProfile() {

        return userProfileService.getProfile();
    }

    // Update Profile
    @PutMapping
    public UserProfileResponse updateProfile(
            @RequestBody UserProfileRequest request) {

        return userProfileService.updateProfile(request);
    }
}