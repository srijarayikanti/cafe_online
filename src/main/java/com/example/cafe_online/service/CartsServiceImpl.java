package com.example.cafe_online.service;

import com.example.cafe_online.dto.RequestCardListDto;
import com.example.cafe_online.dto.ResponseIndividualProductPrice;
import com.example.cafe_online.dto.ResponseUserCartList;
import com.example.cafe_online.dto.requestCartDto;
import com.example.cafe_online.entity.Product;
import com.example.cafe_online.entity.UserCart;
import com.example.cafe_online.repository.UserCartRepository;
import com.example.cafe_online.repository.productRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CartsServiceImpl implements CartsService{

    private final productRepository productRepository;

    private final UserCartRepository userCartRepository;

    public CartsServiceImpl(productRepository productRepository, UserCartRepository userCartRepository) {
        this.productRepository = productRepository;
        this.userCartRepository = userCartRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> addToCart(requestCartDto request) {
        log.debug("Received addToCart request: {}", request);
        int userId = request.getCustomerId();

        // Process each requested item: create or update a User_Cart row
        for (RequestCardListDto item : request.getItems()) {
            int productId = item.getProductId();
            int quantityToAdd = item.getQuantity();

            // Validate product exists
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found: " + productId));
            log.debug("Processing productId {} with quantity {}", productId, quantityToAdd);
            // Upsert: if row exists for user+product, increase quantity; else create new
            UserCart cartRow = userCartRepository.findByUserIdAndProductId(userId, productId)
                    .orElseGet(() -> {
                        UserCart newRow = new UserCart();
                        newRow.setUserId(userId);
                        newRow.setProductId(productId);
                        newRow.setPrice(product.getPrice());
                        newRow.setQuantity(0); // will add below
                        newRow.setTotal_price(0.0);
                        return newRow;
                    });
            log.debug("Current cart row before update: {}", cartRow);
            int newQuantity = cartRow.getQuantity() + quantityToAdd;
            cartRow.setQuantity(newQuantity);
            cartRow.setPrice(product.getPrice()); // ensure latest price stored
            cartRow.setTotal_price(product.getPrice() * newQuantity);

            userCartRepository.save(cartRow);
        }
        log.debug("Final cart rows for user {}: {}", userId, userCartRepository.findByUserId(userId));
        // Build response from saved rows for this user
        List<UserCart> savedRows = userCartRepository.findByUserId(userId);

        ResponseUserCartList response = getResponseUserCartList(userId, savedRows);

        return ResponseEntity.ok(response);
    }

    private static ResponseUserCartList getResponseUserCartList(int userId, List<UserCart> savedRows) {
        ResponseUserCartList response = new ResponseUserCartList();
        response.setUserId(userId);
        log.debug("Building response for userId {} with cart rows: {}", userId, savedRows);
        List<ResponseIndividualProductPrice> productPriceList = new ArrayList<>();
        double total = 0.0;
        for (UserCart row : savedRows) {
            ResponseIndividualProductPrice r = new ResponseIndividualProductPrice();
            r.setProductId(row.getProductId());
            r.setPrice(row.getPrice());
            r.setQuantity(row.getQuantity());
            productPriceList.add(r);

            total += row.getTotal_price();
        }
        response.setProductPriceList(productPriceList);
        response.setTotal_price(total);
        return response;
    }

    @Override
    public ResponseEntity<?> getCartByUserId(int userId) {
        log.debug("Received getCartByUserId request for userId: {}", userId);
        List<UserCart> cartItems = userCartRepository.findByUserId(userId);
        if (cartItems.isEmpty()) {
            return ResponseEntity.ok("Cart is empty for user: " + userId);
        }
        log.debug("Found cart items for userId {}: {}", userId, cartItems);
        ResponseUserCartList response = getResponseUserCartList(userId, cartItems);
        return ResponseEntity.ok(response);
    }
}
