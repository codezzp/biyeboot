package com.example.biyeboot.controller.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private  String password;
    private  String confirmPassword;
    private String token;
    private Integer id;
}
