package com.example.cafe_online.repository;

import com.example.cafe_online.entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Long> {
    Optional<UserCart> findByUserIdAndProductId(int userId, int productId);

    List<UserCart> findByUserId(int userId);
}
