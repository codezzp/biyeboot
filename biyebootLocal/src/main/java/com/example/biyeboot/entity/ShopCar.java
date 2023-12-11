package com.example.biyeboot.entity;

import lombok.Data;

@Data
public class ShopCar {
    private String userId;
    private String merchId;
    private String orderId;
    private int count;
    private String fullname;
    private int unitCost;
    private int sum;
}
