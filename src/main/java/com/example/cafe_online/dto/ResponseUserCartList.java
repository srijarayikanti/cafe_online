package com.example.cafe_online.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseUserCartList {

    private int id;
    private int userId;
    private List<ResponseIndividualProductPrice> productPriceList;
    private double total_price;
}
