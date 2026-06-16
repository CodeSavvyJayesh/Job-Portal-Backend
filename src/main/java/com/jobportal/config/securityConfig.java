package com.jobportal.config;

import com.jobportal.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class securityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

                // Disable CSRF
                .csrf(csrf -> csrf.disable())

                // Enable CORS
                .cors(cors -> {})

                // Authorization Rules
                .authorizeHttpRequests(auth -> auth

                        // Allow preflight requests
                        .requestMatchers(
                                HttpMethod.OPTIONS,
                                "/**"
                        ).permitAll()

                        // Public APIs
                        .requestMatchers(
                                "/api/auth/**"
                        ).permitAll()

                        // USER APIs
                        .requestMatchers(
                                "/api/user/**"
                        ).hasRole("USER")

                        // RECRUITER APIs
                        .requestMatchers(
                                "/api/recruiter/**"
                        ).hasRole("RECRUITER")

                        // ADMIN APIs
                        .requestMatchers(
                                "/api/admin/**"
                        ).hasRole("ADMIN")

                        // Everything else authenticated
                        .anyRequest()
                        .authenticated()
                )

                // Disable Spring Login Page
                .formLogin(form -> form.disable())

                .httpBasic(basic -> basic.disable())

                // JWT Filter
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}