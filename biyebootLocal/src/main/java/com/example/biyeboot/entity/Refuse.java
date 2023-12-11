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
@TableName("sys_refuse")
@ApiModel(value = "Refuse对象", description = "")
public class Refuse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orderId;

    private String refuseReason;


}
