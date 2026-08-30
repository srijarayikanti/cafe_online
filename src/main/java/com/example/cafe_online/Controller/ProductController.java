package com.example.cafe_online.Controller;

import com.example.cafe_online.entity.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Products", description = "Product Management - GET operations are public, POST/PUT/DELETE require ADMIN role")
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
    @PreAuthorize("permitAll()")  // Public - anyone can view products
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
    @PreAuthorize("permitAll()")  // Public - anyone can view products
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
    @PreAuthorize("permitAll()")  // Public - anyone can view products
    ResponseEntity<?> getProductsByCategory(@RequestParam String category);

    @Operation(
            summary = "Save Product Details",
            description = "Save the details of one or more products.",
            tags = {"Product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Products saved successfully", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "404", description = "No products found for the specified category", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/product/saveProductDetails",
            consumes = "application/json",
            produces = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> saveProductDetails(@RequestBody List<Product> products);

    @Operation(
            summary = "Get All Categories",
            description = "Retrieve a list of all product categories.",
            tags = {"Product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categories retrieved successfully", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "404", description = "No products found for the specified category", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))),
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/product/getAllCategories",
            produces = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> getAllCategories();
}
