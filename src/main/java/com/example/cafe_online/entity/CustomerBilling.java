package com.example.cafe_online.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "customer_billing")
@Data
public class CustomerBilling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BillingId")
    private Integer  billingId;

    @Column(name = "CustomerId")
    private int customerId;

    @Column(name = "TotalAmount")
    private double totalAmount;

    @Column(name = "PaymentMethod")
    private String paymentMethod; // (Cash, Card, Mobile Payment)

    @Column(name = "BillingStatus")
    private String billingStatus; // (Paid, Unpaid, Refunded)

    @Column(name = "BillingDate")
    private LocalDate billingDate;

    @Column(name = "BillingTime")
    private LocalTime billingTime;

    @Column(name = "OrderDetails")
    private String orderDetails; // JSON or String representation of the order details

    @Column(name = "TotalItems")
    private int totalItems; // Total number of items in the order
}
