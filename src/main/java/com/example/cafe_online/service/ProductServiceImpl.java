package com.example.cafe_online.service;


import com.example.cafe_online.dto.requestProductDto;
import com.example.cafe_online.entity.Product;
import com.example.cafe_online.repository.productRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final productRepository productRepository;

    public ProductServiceImpl(productRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<?> saveProductDetails(List<requestProductDto> request) {
        // Implement the logic to save product details
        List<Product> products = request.stream().map(dto -> {
            Product p = new Product();
            p.setName(dto.getName());
            p.setPrice(dto.getPrice());
            p.setCategory(dto.getCategory());
            return p;
        }).collect(Collectors.toList());

        // Save all products in bulk
        List<Product> saved = productRepository.saveAll(products);

        // Save the product to the database (you can use a repository for this)
            // For example: productRepository.save(product);
        return ResponseEntity.ok("Product details saved successfully");
    }

    @Override
    public ResponseEntity<?> updateProductDetails(Integer productId, requestProductDto request) {
        // Implement the logic to update product details
        Product existingProduct = productRepository.findById(productId).orElse(null);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
            // Update the product details
        } else {
            existingProduct.setName(request.getName());
            existingProduct.setPrice(request.getPrice());
            existingProduct.setCategory(request.getCategory());
            productRepository.save(existingProduct);
            return ResponseEntity.ok("Product details updated successfully");
        }
    }

    @Override
    public ResponseEntity<?> deleteProductDetails(Integer productId) {
        // Implement the logic to delete product details
        Product existingProduct = productRepository.findById(productId).orElse(null);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(existingProduct);
        return ResponseEntity.ok("Product details deleted successfully");
    }

    @Override
    public ResponseEntity<?> getProductByProductId(Integer productId){
        Product product=productRepository.findById(productId).orElse(null);
        if(product==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getAllCategories() {
        List<String> categories = productRepository.findAll()
                .stream()
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);
    }
}
