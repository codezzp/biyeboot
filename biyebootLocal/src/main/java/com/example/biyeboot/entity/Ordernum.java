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
 * @since 2023-03-03
 */
@Getter
@Setter
@TableName("sys_ordernum")
@ApiModel(value = "Ordernum对象", description = "")
public class Ordernum implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orderId;

    private Integer id;


}
