package com.example.biyeboot.controller.dto;

import lombok.Data;

@Data
public class RefuseDetail extends RefundDetail{
    private String refuseReason;
    private int stepState;
}
