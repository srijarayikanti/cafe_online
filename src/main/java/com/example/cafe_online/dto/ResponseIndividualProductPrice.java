package com.example.cafe_online.dto;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

@Data
public class ResponseIndividualProductPrice {
    private Integer productId;
    private Double price;
    private String productName;
    private Integer quantity;
}
