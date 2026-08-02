package com.example.cafe_online.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestOrdersDto {
    private int customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String loginId;
    private LocalDateTime visitTime;
    private List<RequestCustomerBilling> billingDetails;
}
