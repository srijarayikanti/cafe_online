package com.example.cafe_online.dto;

import lombok.Data;

import java.util.List;

@Data
public class requestCartDto {
    private int customerId;
    private List<RequestCardListDto> items;
}
