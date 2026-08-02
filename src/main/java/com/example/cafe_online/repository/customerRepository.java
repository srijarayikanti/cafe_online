package com.example.cafe_online.repository;


import com.example.cafe_online.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface customerRepository extends JpaRepository<Customer,Integer> {


    @Query(value = "SELECT * FROM customer WHERE email = :email", nativeQuery = true)
    Customer findByEmail(String email);
}
