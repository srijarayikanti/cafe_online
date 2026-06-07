package com.example.cafe_online.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_cart")
@Data
public class UserCart {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "user_id")
        private int userId;

        @Column(name = "product_id")
        private int productId;

        @Column(name = "quantity")
        private int quantity;

        @Column(name = "price")
        private Double price; // price per item

        @Column(name = "total_price")
        private double total_price; // total price for the items in the cart
}
