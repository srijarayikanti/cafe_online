package com.example.cafe_online.service;

import com.example.cafe_online.dto.RequestCustomerBilling;
import com.example.cafe_online.dto.customerRequestDto;
import org.springframework.http.ResponseEntity;

public interface customerService {
    ResponseEntity<?> saveCustomerDetails(customerRequestDto request);

    ResponseEntity<?> saveCustomerBillingDetails(RequestCustomerBilling request);

    ResponseEntity<?> fetchCustomerDetailsByEmailId(String email);
}
