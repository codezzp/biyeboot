package com.example.biyeboot.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TakeOutDTO {
    private Integer orderId;
    private Integer state;
    private String nickName;
    private String orderThing;
    private LocalDateTime orderDate;
    private Integer sum;
    private String address;
    private String payState;
}
