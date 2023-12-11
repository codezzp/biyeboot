package com.example.biyeboot.controller.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserOrderAllHistory {
    private ArrayList<UserDetailOrder> allDetailOrder=new ArrayList<>();//历史消费记录列表
    private Integer sum;//总消费

    public UserOrderAllHistory(ArrayList<UserDetailOrder> allDetailOrder, Integer sum, String lastCostDate, int notPayCount, ArrayList<SingleMerchPayCount> singleMerchPayCountList) {
        this.allDetailOrder = allDetailOrder;
        this.sum = sum;
        this.lastCostDate = lastCostDate;
        this.notPayCount = notPayCount;
        this.singleMerchPayCountList = singleMerchPayCountList;
    }

    private String lastCostDate;//上一次消费的时间
    private int notPayCount;//没有支付的次数
    private ArrayList<SingleMerchPayCount> singleMerchPayCountList=new ArrayList<>();//单个商品消费次数列表
}
