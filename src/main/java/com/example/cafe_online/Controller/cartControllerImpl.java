package com.example.cafe_online.Controller;

import com.example.cafe_online.dto.requestCartDto;
import com.example.cafe_online.service.CartsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
@Slf4j
public class cartControllerImpl implements cartController {
    private final CartsService cartService;

    public cartControllerImpl(CartsService cartService) {
        this.cartService = cartService;
    }

    @Override
    @PostMapping(value = "/addToCart", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addToCart(@RequestBody requestCartDto request) {
        try {
            return cartService.addToCart(request);
        } catch (Exception e) {
            log.error("Error adding to cart: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    @GetMapping(value = "/getCartByUserId", produces = "application/json")
    public ResponseEntity<?> getCartByUserId(@RequestParam int userId) {
        try {
            return cartService.getCartByUserId(userId);
        } catch (Exception e) {
            log.error("Error fetching cart for user ID {}: {}", userId, e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
