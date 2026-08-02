package com.example.cafe_online.Controller;


import com.example.cafe_online.dto.RequestCustomerBilling;
import com.example.cafe_online.dto.customerRequestDto;
import com.example.cafe_online.service.customerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class customerControllerImpl  implements customerController {

    private final customerService customerService;

    public customerControllerImpl(customerService customerService) {
        this.customerService = customerService;
    }

    //private final String BASE_URL = "/api/customer";

    @Override
    public ResponseEntity<?> saveCustomerDetails(customerRequestDto request) {
        try {
            return customerService.saveCustomerDetails(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> saveCustomerBillingDetails(RequestCustomerBilling request) {
        try {
            return customerService.saveCustomerBillingDetails(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> fetchCustomerDetailsByEmailId(String email) {
        try {
            return customerService.fetchCustomerDetailsByEmailId(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
