package com.example.biyeboot.service.impl;

import com.example.biyeboot.controller.dto.RefundDetail;
import com.example.biyeboot.entity.Refund;
import com.example.biyeboot.entity.RefundImg;
import com.example.biyeboot.mapper.RefundImgMapper;
import com.example.biyeboot.mapper.RefundMapper;
import com.example.biyeboot.service.IRefundService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jojo
 * @since 2023-05-22
 */
@Service
public class RefundServiceImpl extends ServiceImpl<RefundMapper, Refund> implements IRefundService {
    @Autowired
    RefundMapper refundMapper;
    @Autowired
    RefundImgMapper refundImgMapper;
    @Override
    public ArrayList<RefundDetail> getRefundDetail() {
        ArrayList<RefundDetail> allRefund = refundMapper.findAllRefund();
        ArrayList<RefundImg> allOrderImg = refundImgMapper.findAllOrderImg();
        for (RefundDetail r:allRefund){
            for (RefundImg i:allOrderImg){
                if (i.getOrderId().equals(r.getOrderId())){
                    r.getImgUrls().add(i.getImgUrl());
                }
            }
        }
        return allRefund;
    }
}
