package com.jobportal.service;

import com.jobportal.dto.UserProfileRequest;
import com.jobportal.dto.UserProfileResponse;
import com.jobportal.entity.User;
import com.jobportal.entity.UserProfile;
import com.jobportal.repository.UserProfileRepository;
import com.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    @Override
    public UserProfileResponse createProfile(
            UserProfileRequest request) {

        User user = userRepository
                .findByEmail("jayeshdhamal03@gmail.com")
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (userProfileRepository.findByUser(user).isPresent()) {
            throw new RuntimeException(
                    "Profile already exists");
        }

        UserProfile profile = UserProfile.builder()
                .user(user)
                .phone(request.getPhone())
                .location(request.getLocation())
                .headline(request.getHeadline())
                .about(request.getAbout())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        UserProfile savedProfile =
                userProfileRepository.save(profile);

        return UserProfileResponse.builder()
                .id(savedProfile.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(savedProfile.getPhone())
                .location(savedProfile.getLocation())
                .headline(savedProfile.getHeadline())
                .about(savedProfile.getAbout())
                .profileImage(savedProfile.getProfileImage())
                .build();
    }

    @Override
    public UserProfileResponse getProfile() {

        User user = userRepository
                .findByEmail("jayeshdhamal03@gmail.com")
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        UserProfile profile =
                userProfileRepository.findByUser(user)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Profile not found"));

        return UserProfileResponse.builder()
                .id(profile.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(profile.getPhone())
                .location(profile.getLocation())
                .headline(profile.getHeadline())
                .about(profile.getAbout())
                .profileImage(profile.getProfileImage())
                .build();
    }

    @Override
    public UserProfileResponse updateProfile(
            UserProfileRequest request) {

        User user = userRepository
                .findByEmail("jayeshdhamal03@gmail.com")
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        UserProfile profile =
                userProfileRepository.findByUser(user)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Profile not found"));

        profile.setPhone(request.getPhone());
        profile.setLocation(request.getLocation());
        profile.setHeadline(request.getHeadline());
        profile.setAbout(request.getAbout());
        profile.setUpdatedAt(LocalDateTime.now());

        UserProfile updatedProfile =
                userProfileRepository.save(profile);

        return UserProfileResponse.builder()
                .id(updatedProfile.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(updatedProfile.getPhone())
                .location(updatedProfile.getLocation())
                .headline(updatedProfile.getHeadline())
                .about(updatedProfile.getAbout())
                .profileImage(updatedProfile.getProfileImage())
                .build();
    }
}