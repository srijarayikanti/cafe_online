package com.example.cafe_online.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class customerRequestDto {

    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String userName;
}
