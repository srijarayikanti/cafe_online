package com.example.cafe_online.repository;


import com.example.cafe_online.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User,Integer> {

    @Query(value = "SELECT * FROM user WHERE LOWER(user_name) = LOWER(:userName)", nativeQuery = true)
    Optional<User> findByUserName(String userName);
}
