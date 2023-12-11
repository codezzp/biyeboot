package com.example.biyeboot.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChartData {
    private List<Integer> last7dateSum;
    private List<String> dateList;
}
