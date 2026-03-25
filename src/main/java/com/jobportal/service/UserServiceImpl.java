package com.jobportal.service;

import com.jobportal.dto.SignupRequest;
import com.jobportal.dto.SignupResponse;
import com.jobportal.entity.Role;
import com.jobportal.entity.User;
import com.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
     private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;

     @Override
    public SignupResponse registerUser(SignupRequest request)
     {
         // check if user is already exists
         if(userRepository.findByEmail(request.getEmail()).isPresent())
         {
             throw new RuntimeException("Email already exist ");
         }

         // convert the DTO -> entity
         User user = User.builder().name(request.getName()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                 .role(Role.USER).build();

         // save the user
         User savedUser= userRepository.save(user);

         // convert entity - > response dto
         return SignupResponse.builder().id(savedUser.getId()).name(savedUser.getName()).email(savedUser.getEmail())
                 .message("User Registered successfully").build();
     }


}