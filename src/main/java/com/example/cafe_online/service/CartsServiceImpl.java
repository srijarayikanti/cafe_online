package com.example.cafe_online.service;

import com.example.cafe_online.dto.RequestCardListDto;
import com.example.cafe_online.dto.ResponseIndividualProductPrice;
import com.example.cafe_online.dto.ResponseUserCartList;
import com.example.cafe_online.dto.requestCartDto;
import com.example.cafe_online.entity.Product;
import com.example.cafe_online.entity.UserCart;
import com.example.cafe_online.exception.ResourceNotFoundException;
import com.example.cafe_online.exception.ValidationException;
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
        
        if (request == null || request.getItems() == null || request.getItems().isEmpty()) {
            throw new ValidationException("Cart items cannot be empty");
        }
        
        int userId = request.getCustomerId();
        if (userId <= 0) {
            throw new ValidationException("Invalid customer ID");
        }

        for (RequestCardListDto item : request.getItems()) {
            int productId = item.getProductId();
            int quantityToAdd = item.getQuantity();

            if (quantityToAdd <= 0) {
                throw new ValidationException("Quantity must be greater than 0 for product: " + productId);
            }

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Product", 
                            "Product not found with ID: " + productId));
            log.debug("Processing productId {} with quantity {}", productId, quantityToAdd);
            
            UserCart cartRow = userCartRepository.findByUserIdAndProductId(userId, productId)
                    .orElseGet(() -> {
                        UserCart newRow = new UserCart();
                        newRow.setUserId(userId);
                        newRow.setProductId(productId);
                        newRow.setPrice(product.getPrice());
                        newRow.setQuantity(0);
                        newRow.setTotal_price(0.0);
                        return newRow;
                    });
            log.debug("Current cart row before update: {}", cartRow);
            int newQuantity = cartRow.getQuantity() + quantityToAdd;
            cartRow.setQuantity(newQuantity);
            cartRow.setPrice(product.getPrice());
            cartRow.setTotal_price(product.getPrice() * newQuantity);

            userCartRepository.save(cartRow);
        }
        log.debug("Final cart rows for user {}: {}", userId, userCartRepository.findByUserId(userId));
        
        List<UserCart> savedRows = userCartRepository.findByUserId(userId);

        ResponseUserCartList response = getResponseUserCartList(userId, savedRows);

        return ResponseEntity.ok(response);
    }

    private ResponseUserCartList getResponseUserCartList(int userId, List<UserCart> savedRows) {
        ResponseUserCartList response = new ResponseUserCartList();
        response.setUserId(userId);
        log.debug("Building response for userId {} with cart rows: {}", userId, savedRows);
        List<ResponseIndividualProductPrice> productPriceList = new ArrayList<>();
        double total = 0.0;
        String productName = "";
        for (UserCart row : savedRows) {
            ResponseIndividualProductPrice r = new ResponseIndividualProductPrice();
            productName= productRepository.getProductNameById(row.getProductId());
            r.setProductId(row.getProductId());
            r.setPrice(row.getPrice());
            r.setProductName(productName);
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
        
        if (userId <= 0) {
            throw new ValidationException("Invalid user ID");
        }
        
        List<UserCart> cartItems = userCartRepository.findByUserId(userId);
        if (cartItems.isEmpty()) {
            return ResponseEntity.ok("Cart is empty for user: " + userId);
        }
        log.debug("Found cart items for userId {}: {}", userId, cartItems);
        ResponseUserCartList response = getResponseUserCartList(userId, cartItems);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> clearCartByUserIdAndProductIdAndQuantity(int userId, int productId, int quantity) {
        log.debug("Received clearCartByUserIdAndProductIdAndQuantity request for userId: {}, productId: {}, quantity: {}", userId, productId, quantity);

        if (userId <= 0 || productId <= 0 || quantity <= 0) {
            throw new ValidationException("Invalid user ID, product ID, or quantity");
        }

        UserCart cartRow = userCartRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("UserCart",
                        "Cart item not found for user ID: " + userId + " and product ID: " + productId));

        if (cartRow.getQuantity() < quantity) {
            throw new ValidationException("Requested quantity exceeds available quantity in cart");
        }

        int newQuantity = cartRow.getQuantity() - quantity;
        
        if (newQuantity <= 0) {
            // Delete the row if quantity becomes 0 or less
            userCartRepository.delete(cartRow);
            log.debug("Deleted cart row for userId {} and productId {} as quantity became 0", userId, productId);
            return ResponseEntity.ok("Cart item deleted successfully for user ID: " + userId + " and product ID: " + productId);
        } else {
            // Update the quantity and total price
            cartRow.setQuantity(newQuantity);
            cartRow.setTotal_price(cartRow.getPrice() * newQuantity);
            userCartRepository.save(cartRow);
            log.debug("Updated cart row for userId {} and productId {}", userId, productId);
            return ResponseEntity.ok("Cart item updated successfully for user ID: " + userId + " and product ID: " + productId);
        }
    }
}

