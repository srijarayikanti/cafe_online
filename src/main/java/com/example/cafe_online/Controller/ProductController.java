package com.example.cafe_online.Controller;

import com.example.cafe_online.entity.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductController {

    @Operation(
        summary = "Save Product Details",
        description = "Save the details of a product in the system.",
        tags = {"Product"},
        responses = {
                @ApiResponse(responseCode = "200", description = "Product details saved successfully", content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Product.class))),
                @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Product.class))),
                @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Product.class))),
        }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/product/getAllProducts",
            produces = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> getAllProducts();

    @Operation(
            summary = "Get Product by Product ID",
            description = "Retrieve the details of a product using its unique product ID.",
            tags = {"Product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product details retrieved successfully", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/product/getProductByProductId",
            produces = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> getProductByProductId(@RequestParam Integer productId);

    @Operation(
            summary = "Get Products by Category",
            description = "Retrieve a list of products that belong to a specific category.",
            tags = {"Product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Products retrieved successfully", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "404", description = "No products found for the specified category", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/product/getProductsByCategory",
            produces = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> getProductsByCategory(@RequestParam String category);
}
