package com.example.biyeboot.controller;
import cn.hutool.core.collection.CollectionUtil;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.biyeboot.controller.dto.*;
import com.example.biyeboot.entity.*;
import com.example.biyeboot.mapper.OrderMapper;
import com.example.biyeboot.mapper.TakeoutMapper;
import com.example.biyeboot.service.IOrderService;
import com.example.biyeboot.service.IOrdernumService;
import com.example.biyeboot.service.ITakeoutService;
import com.example.biyeboot.service.impl.TakeoutServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jojo
 * @since 2023-03-03
 */

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;
    @Autowired
    IOrdernumService ordernumService;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ITakeoutService takeoutService;
    @PostMapping("/saveorder")
    //需要前端提供merchlist[]列表和userID
    public JsonResult<Integer> order(@RequestBody orderDTO2 orderDTO2){
        Integer num = orderService.order(orderDTO2);
        return new JsonResult<Integer>(200,num);
    }
    @PostMapping("/getOrderByUserId")
    public JsonResult<List<List<orderDTO>>> getOrderByUserId(@RequestParam Integer userId){
        //新建一个用户所有订单列表
       List<List<orderDTO>> userAllOrder = new ArrayList<>();
        //读出用户所有的订单号
        List<Integer> orderList = orderMapper.getOid(userId);
        for (Integer orderId:orderList){
            List<orderDTO> orderByOrderId = orderMapper.getOrderByOrderId(orderId);
            userAllOrder.add(orderByOrderId);
        }
        return new JsonResult<>(200,userAllOrder);
    }
    @PostMapping("/genAllOrder")
    public void genAllOrder(){
        orderService.genAllOrder();
    }
    @GetMapping("/genOneOrder")
    public void genOneOrder(@RequestParam Integer orderId){
        orderService.genOneOrder(orderId);
    }
    @GetMapping("/getEachNum")
    public List<Merch> getEachNum(){
        return orderMapper.getEachNum();
    }
    @GetMapping("/orderMerchSumUpdate")
    public void call() {
        try {
            orderMapper.orderMerchSumUpdate();
        }catch (Exception e){
            System.out.println("正常运行");
        }
        System.out.println("自动更新！");
    }
    @GetMapping("/getRecentOrder")
    public List<ShopCar> getRecentOrder(@RequestParam String token){
        String userId = JWT.decode(token).getAudience().get(0);
        List<ShopCar> recentOrder = orderMapper.getRecentOrder(userId);
        return recentOrder;
    }
    @GetMapping("/getUserDetailData")
    public UserOrderAllHistory getUserDetailData(@RequestParam String userId){

        UserOrderAllHistory userOrderAllHistory = orderService.genUserOrderDetailData(Integer.valueOf(userId));
        return userOrderAllHistory;
    }

}
