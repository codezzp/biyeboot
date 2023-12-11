package com.example.biyeboot.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderShow {
    private Integer orderId;
    private String orderThing;
    private LocalDateTime orderDate;
    private Integer sum;
}
