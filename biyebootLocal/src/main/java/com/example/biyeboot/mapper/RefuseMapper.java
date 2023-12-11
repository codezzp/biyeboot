package com.example.biyeboot.mapper;

import com.example.biyeboot.entity.Refuse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jojo
 * @since 2023-05-22
 */
public interface RefuseMapper extends BaseMapper<Refuse> {
    @Insert("insert into sys_refuse VALUES(#{orderId},#{refuseReason})")
    void insertRefuse(Refuse refuse);
    @Update("update sys_refund set step_state=3 where order_id=#{orderId}")
    void alterStepState(int orderId);
    @Update("update sys_refund set refund_state=2 where order_id=#{orderId}")
    void alterRefundState(int orderId);
}
