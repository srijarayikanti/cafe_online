package com.example.cafe_online.service;


import com.example.cafe_online.dto.CreateOrderRequest;
import com.example.cafe_online.dto.RequestOrdersDto;

import com.example.cafe_online.entity.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ordersService {
    ResponseEntity<List<RequestOrdersDto>> fetchAllOrders();

    ResponseEntity<?> saveOrderDetails(RequestOrdersDto request);

    ResponseEntity<Order> createOrder(CreateOrderRequest request);


    Order getOrderById(Long orderId);


    ResponseEntity<?> cancelOrder(Long orderId);
}
