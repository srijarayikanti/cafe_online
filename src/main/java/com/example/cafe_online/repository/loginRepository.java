package com.example.cafe_online.repository;


import com.example.cafe_online.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface loginRepository extends JpaRepository<Login, Integer> {
}
