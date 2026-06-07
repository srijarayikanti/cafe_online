package com.example.cafe_online.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .authorizeHttpRequests(authz -> authz
                        // allow swagger endpoints
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml",
                                "/v3/api-docs.json"
                        ).permitAll()
                        // allow login endpoints you want publicly available
                        .requestMatchers("/login/**").permitAll()
                        // other requests - change to .authenticated() if you want lock down
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}