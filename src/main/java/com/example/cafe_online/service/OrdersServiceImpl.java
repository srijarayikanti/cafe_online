package com.example.cafe_online.service;


import com.example.cafe_online.dto.*;
import com.example.cafe_online.entity.*;
import com.example.cafe_online.repository.OrderRepository;
import com.example.cafe_online.repository.customerBillingRepository;
import com.example.cafe_online.repository.customerRepository;
import com.example.cafe_online.repository.productRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrdersServiceImpl implements ordersService{

    private final customerRepository customerRepository;
    private final customerBillingRepository customerBillingRepository;
    private final OrderRepository orderRepository;
    private final productRepository productRepository;



    @Override
    public ResponseEntity<List<RequestOrdersDto>> fetchAllOrders() {
        List<Customer> customers = customerRepository.findAll();
        List<RequestOrdersDto> ordersList = new ArrayList<>();

        for (Customer customer : customers) {
            // fetch billing info for this customer
            List<CustomerBilling> billings = customerBillingRepository.findByCustomerId(customer.getCustomerId());

            // map CustomerBilling to RequestCustomerBilling
            List<RequestCustomerBilling> billingDetails = new ArrayList<>();
            for (CustomerBilling billing : billings) {
                RequestCustomerBilling billingDto = new RequestCustomerBilling();
                BeanUtils.copyProperties(billing, billingDto);
                billingDetails.add(billingDto);
            }

            // map Customer to RequestOrdersDto
            RequestOrdersDto ordersDto = new RequestOrdersDto();
            BeanUtils.copyProperties(customer, ordersDto);
            ordersDto.setBillingDetails(billingDetails);

            ordersList.add(ordersDto);
        }

        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveOrderDetails(RequestOrdersDto request) {
        // Save customer details
        Customer customer = new Customer();
        BeanUtils.copyProperties(request, customer);
        customerRepository.save(customer);

        // Save billing details
        for (RequestCustomerBilling billingDto : request.getBillingDetails()) {
            CustomerBilling billing = new CustomerBilling();
            BeanUtils.copyProperties(billingDto, billing);
            billing.setCustomerId(customer.getCustomerId()); // set the foreign key
            customerBillingRepository.save(billing);
        }
        return new ResponseEntity<>("Order details saved successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Order> createOrder(CreateOrderRequest request) {

        // 1. Fetch customer
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 2. Create order
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        // 3. Loop through items
        for (OrderItemRequest itemReq : request.getItems()) {

            // Fetch product
            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Create order item
            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            item.setPrice(product.getPrice());
            item.setOrder(order);

            // Calculate total
            totalAmount += product.getPrice() * itemReq.getQuantity();

            orderItems.add(item);
        }

        // 4. Set items and total
        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        // 5. Save (cascade saves items)
        orderRepository.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return (Order) orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }


    @Override
    public ResponseEntity<?> cancelOrder(Long orderId) {

        Order order = (Order) orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() == OrderStatus.PAID) {
            throw new RuntimeException("Cannot cancel a paid order");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return new ResponseEntity<>("Order cancelled successfully", HttpStatus.OK);
    }
}
