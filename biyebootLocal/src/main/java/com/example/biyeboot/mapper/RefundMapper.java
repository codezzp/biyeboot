package com.example.biyeboot.mapper;

import com.example.biyeboot.controller.dto.RefundDetail;
import com.example.biyeboot.controller.dto.RefuseDetail;
import com.example.biyeboot.entity.Refund;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jojo
 * @since 2023-05-22
 */
public interface RefundMapper extends BaseMapper<Refund> {
    @Insert("insert into sys_refund VALUES(#{reason},#{orderId},0,1)")
    void insertRefund(String reason,int orderId);
    @Select("select a.reason,a.order_id,a.refund_state,b.order_thing,b.order_date,b.sum,b.user_id,b.trade_no from (select reason,order_id,refund_state from sys_refund) a\n" +
            "left join sys_orderdetail b on a.order_id=b.order_id \n")
    ArrayList<RefundDetail> findAllRefund();
    @Update("update sys_refund set refund_state=1,step_state=3 where order_id=#{orderId}")
    void alterState(int orderId);
    @Select("select a.reason,a.order_id,a.refund_state,a.step_state,b.order_thing,b.order_date, b.sum,c.refuse_reason from (select reason,order_id,refund_state,step_state from sys_refund) a\n" +
            "left join sys_orderdetail b on a.order_id=b.order_id\n" +
            "left join sys_refuse c on a.order_id=c.order_id \n" +
            "where b.user_id=#{userId}")
    ArrayList<RefuseDetail> findRefundByUserId(int userId);
}
