package com.example.cafe_online.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Double price; // price per item

    // Many items belong to one order
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // Each item refers to one product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
