package com.example.biyeboot.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class orderDTO2 {
    private List<Integer> merchId;
    private String token;
    private boolean takeOutState;
    private String nickName;
    private String address;
    private String phone;
}
