package com.example.biyeboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author jojo
 * @since 2023-05-22
 */
@Getter
@Setter
@TableName("sys_refund")
@ApiModel(value = "Refund对象", description = "")
public class Refund implements Serializable {

    private static final long serialVersionUID = 1L;
    private String reason;
    private Integer orderId;
    private Integer refundState;
    private Integer stepState;

}
