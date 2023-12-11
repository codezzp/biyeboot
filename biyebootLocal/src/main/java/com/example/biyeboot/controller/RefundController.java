package com.example.biyeboot.controller;


import com.example.biyeboot.controller.dto.RefundDetail;
import com.example.biyeboot.controller.dto.RefuseDetail;
import com.example.biyeboot.mapper.RefundMapper;
import com.example.biyeboot.service.IRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jojo
 * @since 2023-05-22
 */
@RestController
@RequestMapping("/refund")
public class RefundController {
    @Autowired
    RefundMapper refundMapper;
    @Autowired
    IRefundService refundService;
    @GetMapping("/insertRefund")
    public void insertRefund(@RequestParam String reason,@RequestParam Integer orderId){
        refundMapper.insertRefund(reason,orderId);
    }
    @GetMapping("/getRefundDetailList")
    public ArrayList<RefundDetail> getRefundDetailList(){
        ArrayList<RefundDetail> refundDetail = refundService.getRefundDetail();
        return refundDetail;
    }
    @GetMapping("/findRefuseByUserId")
    public ArrayList<RefuseDetail> findRefuseByUserId(@RequestParam int userId){
        ArrayList<RefuseDetail> refunds = refundMapper.findRefundByUserId(userId);
        return refunds;
    }

}
