package com.jobportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityConfig {
    // basically this class will be for configuration...
    // it will decide which api will be private , which will be public

    // we need to use @Bean annotation in the class
    // reason is : whenever we use @Configuration annotation we have to use @Bean annotation else
    // if we don't want to use @Bean annotation implicitly we have to use @Component, @Service etc these annotations

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("api/auth/**").permitAll()
                .requestMatchers("api/test/**").permitAll()
                .anyRequest().authenticated()
        );
           return http.build();
    }

}
