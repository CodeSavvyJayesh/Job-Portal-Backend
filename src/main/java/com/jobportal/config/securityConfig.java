package com.jobportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // 🔹 Disable CSRF (for APIs)
                .csrf(csrf -> csrf.disable())

                // 🔹 Authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()  // ✅ signup & login public
                        .requestMatchers("/api/test/**").permitAll()
                        .anyRequest().authenticated()                // 🔐 others protected
                )

                // 🔹 Disable default Spring login
                .formLogin(form -> form.disable())

                // 🔹 Disable basic auth popup
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}