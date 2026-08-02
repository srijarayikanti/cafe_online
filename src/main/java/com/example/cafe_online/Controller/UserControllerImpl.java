package com.example.cafe_online.Controller;


import com.example.cafe_online.dto.RequestUser;
import com.example.cafe_online.service.userService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

private final userService userService;

    public UserControllerImpl(userService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> saveUserDetails(RequestUser requestUser) {
        try {
            return userService.saveUserDetails(requestUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
