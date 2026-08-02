package com.example.cafe_online.Controller;


import com.example.cafe_online.dto.CreateOrderRequest;
import com.example.cafe_online.dto.RequestOrdersDto;
import com.example.cafe_online.entity.Order;
import com.example.cafe_online.service.ordersService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ordersControllerImpl implements ordersController {
    private final ordersService ordersService;


    @Override
    public ResponseEntity<List<RequestOrdersDto>> fetchAllOrders() {
        try {
            return ordersService.fetchAllOrders();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ResponseEntity<?> saveOrderDetails(RequestOrdersDto request) {
     return ordersService.saveOrderDetails(request);
    }

    @Override
    public ResponseEntity<Order> createOrderDetails(CreateOrderRequest request){
        return ordersService.createOrder(request);
    }

    @Override
    public ResponseEntity<?> cancelOrder(Long orderId){
        return ordersService.cancelOrder(orderId);
    }

    @Override
    public Order getOrderById(Long orderId){
        return ordersService.getOrderById(orderId);
    }
}
