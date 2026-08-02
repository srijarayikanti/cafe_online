package com.example.cafe_online.repository;


import com.example.cafe_online.entity.CustomerBilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface customerBillingRepository extends JpaRepository<CustomerBilling, Integer> {
    List<CustomerBilling> findByCustomerId(int customerId);
}
