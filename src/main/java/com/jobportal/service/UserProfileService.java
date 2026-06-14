package com.jobportal.service;

import com.jobportal.dto.UserProfileRequest;
import com.jobportal.dto.UserProfileResponse;

public interface UserProfileService {
    // basically it will act as interface
    UserProfileResponse createProfile(
            UserProfileRequest request
    );

    UserProfileResponse getProfile();

    UserProfileResponse updateProfile(
            UserProfileRequest request
    );
}
