package com.example.cafe_online.service;

import com.example.cafe_online.dto.RequestUser;
import org.springframework.http.ResponseEntity;

public interface userService {
    ResponseEntity<?> saveUserDetails(RequestUser requestUser);
}
