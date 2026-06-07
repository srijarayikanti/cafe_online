package com.example.cafe_online.Controller;

import com.example.cafe_online.dto.requestCartDto;
import com.example.cafe_online.entity.UserCart;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface cartController {

    @Operation(
            summary = "save product details to cart",
            operationId = "addToCart",
            description = "save product details to cart",
            tags = {"Cart"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "User_Cart details saved successfully", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserCart.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserCart.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserCart.class))),
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/cart/addToCart",
            produces = "application/json",
            consumes = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> addToCart(@RequestBody requestCartDto request);

    @Operation(
            summary = "get cart details by user id",
            operationId = "getCartByUserId",
            description = "get cart details by user id",
            tags = {"Cart"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cart details fetched successfully", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserCart.class))),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserCart.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserCart.class))),
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/cart/getCartByUserId",
            produces = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> getCartByUserId(@RequestParam int userId);
}
