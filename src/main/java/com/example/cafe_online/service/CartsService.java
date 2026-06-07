package com.example.cafe_online.service;

import com.example.cafe_online.dto.requestCartDto;
import org.springframework.http.ResponseEntity;

public interface CartsService {
    ResponseEntity<?> addToCart(requestCartDto request);

    ResponseEntity<?> getCartByUserId(int userId);

}
