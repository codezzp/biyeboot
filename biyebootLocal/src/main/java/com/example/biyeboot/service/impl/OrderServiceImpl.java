package com.example.biyeboot.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.biyeboot.controller.dto.*;
import com.example.biyeboot.entity.Order;
import com.example.biyeboot.entity.Orderdetail;
import com.example.biyeboot.entity.Ordernum;
import com.example.biyeboot.entity.Takeout;
import com.example.biyeboot.mapper.OrderMapper;
import com.example.biyeboot.mapper.OrderdetailMapper;
import com.example.biyeboot.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.biyeboot.service.IOrderdetailService;
import com.example.biyeboot.service.IOrdernumService;
import com.example.biyeboot.service.ITakeoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jojo
 * @since 2023-03-03
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
@Autowired
OrderMapper orderMapper;
@Autowired
IOrderdetailService orderdetailService;
@Autowired
IOrdernumService ordernumService;
@Autowired
ITakeoutService takeoutService;
    @Override
    public void genAllOrder() {
        List<Integer> allOrderId = orderMapper.getAllOrderId();
        List<Orderdetail> allOrder = new ArrayList<>();
        for (Integer orderId:allOrderId){
            Orderdetail orderdetail = new Orderdetail();
            StringBuffer orderThing = new StringBuffer("");
            List<orderDTO> orderByOrderId = orderMapper.getOrderByOrderId(orderId);
            int sum =0;
            for (orderDTO order:orderByOrderId){
                orderThing.append(order.getFullname()).append(" * "). append(order.getCount()).append(',');
                orderdetail.setOrderId(order.getOrderId());
                sum=sum+order.getSum();
                orderdetail.setOrderDate(order.getOrderDate());
                orderdetail.setUserId(order.getUserId());
                orderdetail.setOrderDate(order.getOrderDate());
            }
            orderdetail.setSum(sum);
            String oT = orderThing.deleteCharAt(orderThing.length() - 1).toString();
            orderdetail.setOrderThing(oT);
            orderdetailService.save(orderdetail);
        }
    }

    @Override
    public void genOneOrder(Integer orderId) {
        StringBuffer orderThing = new StringBuffer("");
        Orderdetail orderdetail = new Orderdetail();
        List<orderDTO> orderByOrderId = orderMapper.getOrderByOrderId(orderId);
        int sum=0;
        for (orderDTO order:orderByOrderId){
            orderThing.append(order.getFullname()).append(" * "). append(order.getCount()).append(',');
            orderdetail.setOrderId(order.getOrderId());
            sum=sum+order.getSum();
            orderdetail.setOrderDate(order.getOrderDate());
            orderdetail.setUserId(order.getUserId());
        }
        orderdetail.setSum(sum);
        String oT = orderThing.deleteCharAt(orderThing.length() - 1).toString();
        orderdetail.setOrderThing(oT);
        orderdetail.setPayState("未支付");
        orderdetailService.save(orderdetail);
    }

    @Override
    public Integer order(orderDTO2 orderDTO2) {
        QueryWrapper<Ordernum> ordernumQueryWrapper = new QueryWrapper<Ordernum>();
        ordernumQueryWrapper.eq("id",1);
        Ordernum orderNum=ordernumService.getOne(ordernumQueryWrapper);
        Integer userId = Integer.parseInt(JWT.decode(orderDTO2.getToken()).getAudience().get(0));
        //从订单号表里读出订单号
        Integer num=orderNum.getOrderId();
        //将merch_id从列表里读出来，和订单号，用户id一一对应的搞到order里面去，一次次的写入到sys_order表里
        for (int merchid:orderDTO2.getMerchId()){
            Order order=new Order();
            order.setMerchId(merchid);
            order.setUserId(userId);
            order.setState(1);
            System.out.println(order.getMerchId()+"  "+order.getUserId());
            order.setOrderId(num);
            save(order);
        }
        //订单号加1，再写回订单号表里面
        num+=1;
        orderNum.setOrderId(num);
        ordernumService.saveOrUpdate(orderNum);
        System.out.println(orderDTO2.toString());
        if(orderDTO2.isTakeOutState()){
            Takeout takeout = new Takeout();
            takeout.setOrderId(num-1);
            takeout.setUserId(userId);
            takeout.setNickName(orderDTO2.getNickName());
            takeout.setAddress(orderDTO2.getAddress());
            takeoutService.save(takeout);
        }
        return num-1;


    }

    @Override
    public UserOrderAllHistory genUserOrderDetailData(Integer userId) {
        ArrayList<UserDetailOrder> userDetailOrderList = orderMapper.getUserDetailOrderList(userId);
        Integer sum = orderMapper.getSum(userId);
        String lastCostDate = orderMapper.getLastCostDate(userId);
        Integer count = orderMapper.getnotPayCount(userId);
        ArrayList<SingleMerchPayCount> singMerchPayCount = orderMapper.getSingMerchPayCount(userId);
        return new UserOrderAllHistory(userDetailOrderList,sum,lastCostDate,count,singMerchPayCount);


    }
}
