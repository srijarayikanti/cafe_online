package com.example.cafe_online.Controller;

import com.example.cafe_online.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.cafe_online.repository.productRepository;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class ProductControllerImpl implements ProductController {

    private final productRepository productRepository;

    public ProductControllerImpl(productRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<?> getAllProducts() {
        // Implementation for getting all products
        List<Product> product =productRepository.findAll();
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<?> getProductByProductId(Integer productId) {
        // Implementation for getting a product by its ID
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> getProductsByCategory(String category) {
        // Implementation for getting products by category
        List<Product> products = productRepository.findByCategory(category);
        return ResponseEntity.ok(products);
    }

}
