package com.example.biyeboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2023-05-22
 */
@Getter
@Setter
@TableName("sys_refund_img")
@ApiModel(value = "RefundImg对象", description = "")
public class RefundImg implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orderId;

    private String imgUrl;


}
