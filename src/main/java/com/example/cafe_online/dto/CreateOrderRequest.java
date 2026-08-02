package com.example.cafe_online.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private int customerId;
    private List<OrderItemRequest> items;
}
