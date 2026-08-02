package com.example.cafe_online.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class customerRequestDto {

    private int customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String loginId;
    private LocalDateTime visitTime;
}
