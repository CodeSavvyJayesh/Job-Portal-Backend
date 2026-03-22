package com.jobportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class passwordConfig {
    // this will always store encrypted password
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

        // eg:
      //  Input: 123456
     //   Stored: $2a$10$Xyz....
    }
}
