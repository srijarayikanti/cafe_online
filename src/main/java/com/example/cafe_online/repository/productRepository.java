package com.example.cafe_online.repository;

import com.example.cafe_online.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(String category);

    @Query("SELECT p.name FROM Product p WHERE p.id = :productId")
    String getProductNameById(@Param("productId") int productId);
}
