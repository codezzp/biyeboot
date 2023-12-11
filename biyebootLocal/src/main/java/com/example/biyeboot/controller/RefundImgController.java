package com.example.biyeboot.controller;


import com.example.biyeboot.mapper.RefundImgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jojo
 * @since 2023-05-22
 */
@RestController
@RequestMapping("/refund-img")
public class RefundImgController {
    @Autowired
    RefundImgMapper refundImgMapper;
    @GetMapping("/insertOrderIdAndImg")
    public void insertOrderIdAndImg(@RequestParam int orderId,@RequestParam String imgUrl){
        refundImgMapper.insertOrderIdAndImg(orderId,imgUrl);
    }
}
