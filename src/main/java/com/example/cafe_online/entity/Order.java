package com.example.cafe_online.entity;


import com.example.cafe_online.dto.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // CREATED, PAID, CANCELLED

    // Many orders belong to one customer
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // One order has many order items
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;


}
