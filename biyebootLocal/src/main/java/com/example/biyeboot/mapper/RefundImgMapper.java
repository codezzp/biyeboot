package com.example.biyeboot.mapper;

import com.example.biyeboot.entity.RefundImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jojo
 * @since 2023-05-22
 */
public interface RefundImgMapper extends BaseMapper<RefundImg> {
    @Insert("insert into sys_refund_img VALUES(#{orderId},#{imgUrl})")
    void insertOrderIdAndImg(int orderId,String imgUrl);
    @Select("select img_url from sys_refund_img where order_id=#{orderId}")
    ArrayList<String> findImgByOrderId(int orderId);

    @Select("select * from sys_refund_img")
    ArrayList<RefundImg> findAllOrderImg();
}
