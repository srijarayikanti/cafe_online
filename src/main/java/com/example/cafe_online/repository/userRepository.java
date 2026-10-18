package com.example.cafe_online.repository;


import com.example.cafe_online.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUserNameIgnoreCase(String userName);
}
