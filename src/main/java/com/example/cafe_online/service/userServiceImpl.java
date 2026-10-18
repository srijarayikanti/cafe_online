package com.example.cafe_online.service;


import com.example.cafe_online.Util.jwtUtil;
import com.example.cafe_online.dto.RequestUser;
import com.example.cafe_online.entity.User;
import com.example.cafe_online.repository.userRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class userServiceImpl implements userService {

    private final userRepository userRepository;
    private final jwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public userServiceImpl(userRepository userRepository, jwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public ResponseEntity<?> saveUserDetails(RequestUser requestUser) {
        Optional<User> userOpt = userRepository.findByUserNameIgnoreCase(requestUser.getUserName());
        log.debug("Checking if user exists: {}", requestUser.getUserName());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username");
        }
        User user = userOpt.get();
        
        // Check password: support both encrypted and plain text (for migration)
        boolean passwordMatches = passwordEncoder.matches(requestUser.getPassword(), user.getPassword()) 
                                  || user.getPassword().equals(requestUser.getPassword());
        
        if (!passwordMatches) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password");
        }
        
        // If password was plain text, encrypt it now (migration)
        if (user.getPassword().equals(requestUser.getPassword())) {
            user.setPassword(passwordEncoder.encode(requestUser.getPassword()));
            userRepository.save(user);
            log.debug("Migrated plain text password to encrypted for user: {}", user.getUserName());
        }
        
        // Generate JWT token for user
        String token = jwtUtil.generateTokenForUsername(user.getUserName());
        log.debug("Generated JWT token for user {}: {}", user.getUserName(), token);

        return ResponseEntity.ok().body(
                Map.of(
                        "message", "Login Successful",
                        "token", token,
                        "userId", user.getUserId(),
                        "username", user.getUserName(),
                        "role", user.getRole()
                )
        );
    }

    @Override
    public ResponseEntity<?> addUser(RequestUser requestUser) {
        Optional<User> userOpt = userRepository.findByUserNameIgnoreCase(requestUser.getUserName());
        log.debug("Checking if user exists for addition: {}", requestUser.getUserName());
        if (userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        User newUser = new User();
        newUser.setUserName(requestUser.getUserName());
        newUser.setPassword(passwordEncoder.encode(requestUser.getPassword()));
        newUser.setRole(requestUser.getRole());

        userRepository.save(newUser);
        log.debug("Added new user: {}", newUser.getUserName());

        return ResponseEntity.ok().body("User added successfully");
    }
}
