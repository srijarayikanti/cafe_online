package com.example.cafe_online.repository;

import com.example.cafe_online.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(String category);
}
