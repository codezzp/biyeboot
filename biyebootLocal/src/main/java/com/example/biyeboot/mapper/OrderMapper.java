package com.example.biyeboot.mapper;

import com.example.biyeboot.controller.dto.SingleMerchPayCount;
import com.example.biyeboot.controller.dto.UserDTO;
import com.example.biyeboot.controller.dto.UserDetailOrder;
import com.example.biyeboot.controller.dto.orderDTO;
import com.example.biyeboot.entity.Merch;
import com.example.biyeboot.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.biyeboot.entity.ShopCar;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jojo
 * @since 2023-03-03
 */
public interface OrderMapper extends BaseMapper<Order> {
        @Select("SELECT order_id FROM sys_order\n" +
                "where user_id=#{userId}\n" +
                "GROUP BY order_id")
        List<Integer> getOid(Integer userId);
        @Select("SELECT s.user_id,s.merch_id,count(merch_id) count,s.order_id ,m.fullname,m.unit_cost,CONVERT(unit_cost,DECIMAL)*CONVERT(COUNT(merchID),DECIMAL) sum,s.date order_date \n" +
                "FROM sys_order s,merch m\n" +
                "where s.merch_id=m.merchID and order_id =#{orderId}\n" +
                "GROUP BY merch_id,user_id,order_id")
        List<orderDTO> getOrderByOrderId(Integer orderId);
        @Select("SELECT order_id FROM sys_order\n" +
                "GROUP BY order_id\n")
        List<Integer> getAllOrderId();
        @Select("select merch_id,count(merch_id) num from sys_order group by merch_id")
        List<Merch> getEachNum();
        @Select("call orderMerchSum()")
        void orderMerchSumUpdate();
        @Select("select a.user_id,a.merch_id,a.order_id,a.count,b.fullname,b.unit_cost,a.count*b.unit_cost as sum\n" +
                "from \n" +
                "(select user_id,merch_id,order_id,count(*) count from sys_order where order_id=(select DISTINCT order_id from sys_order where date=(select MAX(date) from sys_order where user_id=#{userId})) GROUP BY user_id,merch_id,order_id)a\n" +
                "left join \n" +
                "merch b\n" +
                "on a.merch_id=b.merchID")
        List<ShopCar> getRecentOrder(String userId);
/*用户订单数据详细分析，以下内容体现智慧化*/
        @Select("SELECT order_thing,order_date FROM `sys_orderdetail` where user_id=#{userId}")
        ArrayList<UserDetailOrder> getUserDetailOrderList(Integer userId);

        @Select("select sum(sum) sum from sys_orderdetail WHERE user_id=#{userId}")
        Integer getSum(Integer userId);

        @Select("select SUBSTR(MAX(order_date),1,10) last_cost_date from sys_orderdetail where user_id=#{userId}")
        String getLastCostDate(Integer userId);

        @Select("select count(*) not_pay_count from sys_orderdetail where pay_state!='已支付' and user_id=#{userId}")
        Integer getnotPayCount(Integer userId);

        @Select("select a.count,b.fullname from (select merch_id,count(*) count from sys_order where user_id=#{userId} GROUP BY merch_id) a\n" +
                "LEFT JOIN merch b\n" +
                "on a.merch_id=b.merchID")
        ArrayList<SingleMerchPayCount> getSingMerchPayCount(Integer userId);
}

