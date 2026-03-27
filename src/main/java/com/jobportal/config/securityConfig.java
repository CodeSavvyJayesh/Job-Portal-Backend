package com.jobportal.config;

import com.jobportal.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class securityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        System.out.println("Security Config Loaded 🔥"); // debug

        http
                // 🔹 Disable CSRF
                .csrf(csrf -> csrf.disable())

                // 🔹 Authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // only login/signup open
                        .anyRequest().authenticated() // EVERYTHING else protected 🔐
                )

                // 🔹 Disable default login
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())

                // 🔥 ADD JWT FILTER
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}