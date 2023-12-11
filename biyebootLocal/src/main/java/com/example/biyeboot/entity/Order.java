package com.example.biyeboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author jojo
 * @since 2023-03-03
 */
@Getter
@Setter
@ApiModel(value = "Order对象", description = "")
@TableName("sys_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("餐品id")
    private Integer merchId;

    @ApiModelProperty("订单状态码")
    private Integer state;
    @ApiModelProperty("订单号")
    private Integer orderId;

    private LocalDateTime date;



}
