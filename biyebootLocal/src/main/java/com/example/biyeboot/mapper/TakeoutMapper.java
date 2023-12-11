package com.example.biyeboot.mapper;

import com.example.biyeboot.controller.dto.TakeOutDTO;
import com.example.biyeboot.entity.JsonResult;
import com.example.biyeboot.entity.Takeout;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jojo
 * @since 2023-03-16
 */
public interface TakeoutMapper extends BaseMapper<Takeout> {
    @Select("select t.order_id,t.state,t.address,t.nick_name,o.order_thing,o.order_date,o.sum,o.pay_state from sys_takeout t ,sys_orderdetail o where t.order_id=o.order_id and t.user_id=#{userId} LIMIT #{pageNum},#{pageSize}")
    List<TakeOutDTO> getAllTakeOutByUser(Integer userId,Integer pageNum,Integer pageSize);
    @Select("select count(t.order_id) count from sys_takeout t ,sys_orderdetail o where t.order_id=o.order_id and t.user_id=#{userId}")
    Integer getAllTakeOutByUserToal(Integer userId);

    @Select("select t.order_id,t.state,t.address,t.nick_name,o.order_thing,o.order_date,o.sum,o.pay_state from sys_takeout t ,sys_orderdetail o where t.order_id=o.order_id LIMIT #{pageNum},#{pageSize}")
    List<TakeOutDTO> getAllTakeOut(Integer pageNum,Integer pageSize);
    @Select("select count(t.order_id) count from sys_takeout t ,sys_orderdetail o where t.order_id=o.order_id")
    Integer getAllTakeOutTotal();

    @Select("select t.order_id,t.state,t.address,t.nick_name,o.order_thing,o.order_date,o.sum,o.pay_state from sys_takeout t ,sys_orderdetail o where t.order_id=o.order_id and t.state=0 LIMIT #{pageNum},#{pageSize}")
    List<TakeOutDTO> getAllTakeOutNotFinish(Integer pageNum,Integer pageSize);
    @Select("select count(t.order_id) count from sys_takeout t ,sys_orderdetail o where t.order_id=o.order_id and t.state=0")
    Integer getAllTakeOutNotFinishTotal();

    @Update("UPDATE sys_takeout set state=#{state} where order_id=#{orderId}")
    void changeState(Integer state,Integer orderId);

}
