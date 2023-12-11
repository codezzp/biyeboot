package com.example.biyeboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.biyeboot.entity.Orderdetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jojo
 * @since 2023-03-09
 */
public interface OrderdetailMapper extends BaseMapper<Orderdetail> {
    //查询今日销售总额
    @Select("SELECT sum(sum) FROM sys_orderdetail\n" +
            "WHERE DATE_FORMAT(order_date,'%y-%m-%d')=DATE_FORMAT(SYSDATE(),'%y-%m-%d')")
    Integer getDaySum();
//    查询多少天以内的订单
    @Select("SELECT * FROM sys_orderdetail WHERE TO_DAYS(NOW()) - TO_DAYS(order_date) <= #{flag}\n" +
            "LIMIT #{pageNum},#{pageSize}")
    List<Orderdetail> getOrderByInterValDate(Integer pageNum, Integer pageSize, Integer flag);
    //    与上面绑定，查询多少天以内的订单总和，为分页做准备
    @Select("SELECT count(*) FROM sys_orderdetail WHERE TO_DAYS(NOW()) - TO_DAYS(order_date) <= #{flag}")
    Integer getOrderByInterValDateToal(Integer flag);

    @Select("SELECT * FROM sys_orderdetail WHERE TO_DAYS(NOW()) - TO_DAYS(order_date) = #{flag}\n" +
            "LIMIT #{pageNum},#{pageSize}")
    List<Orderdetail> getOrderByDate(Integer pageNum, Integer pageSize, Integer flag);
    //    与上面绑定，查询多少天以内的订单总和，为分页做准备
    @Select("SELECT count(*) FROM sys_orderdetail WHERE TO_DAYS(NOW()) - TO_DAYS(order_date) = #{flag}")
    Integer getOrderByDateToal(Integer flag);

//    删除指定orderid的订单
    @Delete("DELETE FROM sys_orderdetail\n" +
            "WHERE order_id=#{id}")
    boolean removeById(Integer id);
//    批量删除指定ids的订单
    @Delete("<script>\n" +
            "        delete from sys_orderdetail where order_id in \n" +
            "        <foreach collection='ids' item='id' open='(' separator=',' close=')'>#{id}</foreach>\n" +
            "                  </script>")
    boolean removeBatchByIds(List<Integer> ids);
//    查询指定用户的今天或昨天的订单
    @Select("SELECT * FROM `sys_orderdetail` WHERE DATEDIFF(SYSDATE(),order_date)=#{flag} and user_id=#{userId} LIMIT #{pageNum},#{pageSize}")
    List<Orderdetail> getMyOrderByDate(Integer pageNum,Integer pageSize,Integer flag,Integer userId);
//    查询指定用户的今天或昨天的订单总和
    @Select("SELECT COUNT(*) FROM `sys_orderdetail` WHERE DATEDIFF(SYSDATE(),order_date)=#{flag} and user_id=#{userId}")
    Integer getMyOrderByDateToal(Integer flag,Integer userId);
    //    查询指定用户的七天或一个月的订单
    @Select("SELECT * FROM `sys_orderdetail` WHERE DATEDIFF(SYSDATE(),order_date)<=#{flag} and user_id=#{userId} LIMIT #{pageNum},#{pageSize}")
    List<Orderdetail> getMyOrderByDates(Integer pageNum,Integer pageSize,Integer flag,Integer userId);
    //    查询指定用户的七天或一个月的订单总和
    @Select("SELECT COUNT(*) FROM `sys_orderdetail` WHERE DATEDIFF(SYSDATE(),order_date)<=#{flag} and user_id=#{userId}")
    Integer getMyOrderByDatesToal(Integer flag,Integer userId);

    @Select("select sum(sum) from sys_orderdetail where day(SYSDATE())-DAY(order_date)=#{date}")
    Integer getDaySumByDate(Integer date);
    @Update("update sys_orderdetail set pay_state='已支付' where order_id=#{orderId}")
    void alterPayState(Integer orderId);

    @Update("update sys_orderdetail set trade_no=#{tradeNo} where order_id=#{orderId}")
    void insertTradeNo(String tradeNo,Integer orderId);
}
