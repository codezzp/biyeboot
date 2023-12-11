package com.example.biyeboot.service;

import com.example.biyeboot.controller.dto.RefundDetail;
import com.example.biyeboot.entity.Refund;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jojo
 * @since 2023-05-22
 */
public interface IRefundService extends IService<Refund> {
    ArrayList<RefundDetail> getRefundDetail();
}
