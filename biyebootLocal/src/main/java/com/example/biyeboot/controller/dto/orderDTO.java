package com.example.biyeboot.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class orderDTO {
    private List<Integer> merchId;
    private int userId;
    private int orderId;
    private int merchID;
    private String fullname;
    private long unitCost;
    private int count;
    private LocalDateTime orderDate;
    private Integer sum;
}
