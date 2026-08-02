package com.example.cafe_online.service;


import com.example.cafe_online.Util.jwtUtil;
import com.example.cafe_online.dto.RequestUser;
import com.example.cafe_online.entity.User;
import com.example.cafe_online.repository.userRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class userServiceImpl implements userService {

    private final userRepository userRepository;
    private final jwtUtil jwtUtil;

    public userServiceImpl(userRepository userRepository, jwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }
    @Override
    public ResponseEntity<?> saveUserDetails(RequestUser requestUser) {
        Optional<User> userOpt = userRepository.findByUserName(requestUser.getUserName());
        log.debug("Checking if user exists: {}", requestUser.getUserName());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username");
        }
        User user = userOpt.get();
        // Check if passwords match
        if (user.getPassword().equals(requestUser.getPassword())) {
            // In a real app, you would return a JWT token here
            String token = jwtUtil.generateTokenForUsername(user.getUserName());
            log.debug("Generated JWT token for user {}: {}", user.getUserName(), token);

            return ResponseEntity.ok().body(
                    Map.of(
                            "message", "Login Successful",
                            "token", token
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password");
        }
    }
}
