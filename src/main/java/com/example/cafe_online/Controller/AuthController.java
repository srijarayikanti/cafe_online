package com.example.cafe_online.Controller;

import com.example.cafe_online.entity.User;
import com.example.cafe_online.repository.userRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final userRepository userRepository;

    public AuthController(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        return userRepository.findByUserNameIgnoreCase(username)
                .map(user -> ResponseEntity.ok().body(Map.of(
                        "userId", user.getUserId(),
                        "username", user.getUserName(),
                        "role", user.getRole()
                )))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                        "error", "User not found"
                )));
    }
}