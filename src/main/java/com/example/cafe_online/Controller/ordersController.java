package com.example.cafe_online.Controller;

import com.example.cafe_online.dto.CreateOrderRequest;
import com.example.cafe_online.dto.RequestOrdersDto;
import com.example.cafe_online.entity.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/orders")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Orders", description = "Operations related to orders")
public interface ordersController {

    @Operation(
            summary="Fetch all orders",
            operationId="fetchAllOrders",
            tags="Orders",
            responses = {
                    @ApiResponse(responseCode = "200",description = "saveCustomer saved successfully",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "401",description = "Error occurred",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Order.class)))

            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer/fetchAllOrders",
            produces = "application/json"
    )
    @CrossOrigin
    ResponseEntity<List<RequestOrdersDto>> fetchAllOrders();

    @Operation(
            summary="saveOrderDetails",
            operationId="saveOrderDetails",
            tags="Orders",
            responses = {
                    @ApiResponse(responseCode = "200",description = "saveCustomer saved successfully",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "401",description = "Error occurred",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Order.class)))

            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/customer/saveOrderDetails",
            produces = "application/json",
            consumes = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> saveOrderDetails(@RequestBody RequestOrdersDto request);

    @Operation(
            summary="saveOrderDetails",
            operationId="saveOrderDetails",
            tags="Orders",
            responses = {
                    @ApiResponse(responseCode = "200",description = "saveCustomer saved successfully",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "401",description = "Error occurred",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Order.class)))

            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/customer/createOrderDetails",
            produces = "application/json",
            consumes = "application/json"
    )
    @CrossOrigin
    ResponseEntity<Order> createOrderDetails(@RequestBody CreateOrderRequest request);

    @Operation(
            summary="saveOrderDetails",
            operationId="saveOrderDetails",
            tags="Orders",
            responses = {
                    @ApiResponse(responseCode = "200",description = "saveCustomer saved successfully",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "401",description = "Error occurred",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Order.class)))

            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/customer/cancelOrder",
            produces = "application/json",
            consumes = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> cancelOrder(Long orderId);

    @Operation(
            summary="getOrderById",
            operationId="getOrderById",
            tags="Orders",
            responses = {
                    @ApiResponse(responseCode = "200",description = "saveCustomer saved successfully",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "401",description = "Error occurred",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Order.class)))

            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer/getOrderById",
            produces = "application/json"
    )
    @CrossOrigin
    Order getOrderById(Long orderId);
}
