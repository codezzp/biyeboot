package com.example.biyeboot.service;

import com.example.biyeboot.controller.dto.OrderShow;
import com.example.biyeboot.controller.dto.UserOrderAllHistory;
import com.example.biyeboot.controller.dto.orderDTO2;
import com.example.biyeboot.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.biyeboot.entity.Orderdetail;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jojo
 * @since 2023-03-03
 */
public interface IOrderService extends IService<Order> {
    void genAllOrder();
    void genOneOrder(Integer orderId);
    Integer order(orderDTO2 orderDTO2);
    UserOrderAllHistory genUserOrderDetailData(Integer userId);
}
