package com.example.cafe_online.Controller;

import com.example.cafe_online.entity.Product;
import com.example.cafe_online.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.cafe_online.repository.productRepository;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@AllArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final productRepository productRepository;
    private final ProductService productService;



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

    @Override
    public ResponseEntity<?> saveProductDetails(List<Product> products) {
        // Implementation for saving product details
        List<Product> savedProducts = productRepository.saveAll(products);
        return ResponseEntity.ok(savedProducts);
    }

    @Override
    public ResponseEntity<?> getAllCategories() {
        // Implementation for getting all categories

        return productService.getAllCategories();
    }
}
