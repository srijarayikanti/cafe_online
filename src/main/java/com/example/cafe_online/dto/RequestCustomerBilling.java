package com.example.cafe_online.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RequestCustomerBilling {
    //private int billingId;
    private int customerId;
    private double totalAmount;
    private String paymentMethod; // (Cash, Card, Mobile Payment)
    private String billingStatus; // (Paid, Unpaid, Refunded)
    private LocalDate billingDate;
    private LocalTime billingTime;
    private String orderDetails; // JSON or String representation of the order details
    private int totalItems; // Total number of items in the order
}
