package com.example.biyeboot.controller.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class RefundDetail {
    private String reason;
    private int orderId;
    private int refundState;
    private String orderThing;
    private String orderDate;
    private int sum;
    private int userId;
    private String tradeNo;
    private ArrayList<String> imgUrls=new ArrayList<>();
}
