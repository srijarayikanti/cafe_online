package com.example.cafe_online.service;

import com.example.cafe_online.dto.requestProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<?> saveProductDetails(List<requestProductDto> request);

    ResponseEntity<?> updateProductDetails(Integer productId, requestProductDto request);

    ResponseEntity<?> deleteProductDetails(Integer productId);

    ResponseEntity<?> getProductByProductId(Integer productId);

    ResponseEntity<?> getAllProducts();

    ResponseEntity<?> getAllCategories();
}
