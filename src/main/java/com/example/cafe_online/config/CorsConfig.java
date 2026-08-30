package com.example.cafe_online.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${CORS_ALLOWED_ORIGINS:http://localhost:3000,http://localhost:5173,http://localhost:5174}")
    private String allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        String[] origins = allowedOrigins
                .split(",");

        for (int i = 0; i < origins.length; i++) {
            origins[i] = origins[i].trim();
        }

        registry.addMapping("/**")
                .allowedOrigins(origins)
                .allowedMethods(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "PATCH",
                        "OPTIONS"
                )
                // Explicit headers instead of wildcard when using allowCredentials(true)
                // This is required by CORS spec and prevents browser security violations
                .allowedHeaders("Authorization", "Content-Type", "X-Requested-With", "Cookie")
                .exposedHeaders("Authorization")
                .allowCredentials(true)
                .maxAge(3600);
    }
}