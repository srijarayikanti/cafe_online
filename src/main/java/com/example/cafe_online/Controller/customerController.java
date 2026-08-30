package com.example.cafe_online.Controller;


import com.example.cafe_online.dto.RequestCustomerBilling;
import com.example.cafe_online.dto.customerRequestDto;
import com.example.cafe_online.entity.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/customer")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Customer", description = "Operations related to customer management")
public interface customerController {

    @Operation(
            summary="saveCustomerDetails",
            operationId="saveCustomerDetails",
            tags="Customer",
            responses = {
                    @ApiResponse(responseCode = "200",description = "saveCustomer saved successfully",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Customer.class))),
                    @ApiResponse(responseCode = "401",description = "Error occurred",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Customer.class)))

            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/customer/saveCustomerDetails",
            produces = "application/json",
            consumes = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> saveCustomerDetails(@RequestBody customerRequestDto request);

    @Operation(
            summary="saveCustomerBillingDetails",
            operationId="saveCustomerBillingDetails",
            tags="Customer",
            responses = {
                    @ApiResponse(responseCode = "200",description = "saveCustomer saved successfully",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Customer.class))),
                    @ApiResponse(responseCode = "401",description = "Error occurred",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Customer.class)))

            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/customer/saveCustomerBillingDetails",
            produces = "application/json",
            consumes = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> saveCustomerBillingDetails(@RequestBody RequestCustomerBilling request);

    @Operation(
            summary="fetchCustomerDetailsByEmailId",
            operationId="fetchCustomerDetailsByEmailId",
            tags="Customer",
            responses = {
                    @ApiResponse(responseCode = "200",description = "saveCustomer saved successfully",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Customer.class))),
                    @ApiResponse(responseCode = "401",description = "Error occurred",content=@Content(mediaType ="application/json",
                            schema = @Schema(implementation = Customer.class)))

            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/customer/fetchCustomerDetailsByEmailId",
            produces = "application/json"
    )
    @CrossOrigin
    ResponseEntity<?> fetchCustomerDetailsByEmailId(String email);
}
