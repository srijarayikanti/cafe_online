package com.example.cafe_online.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payment_status")
@Data
public class Payment_Status {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @Column(name = "status")
        private String status;

}
