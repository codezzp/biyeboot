package com.example.biyeboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @since 2023-03-09
 */
@Getter
@Setter
  @TableName("sys_orderdetail")
@ApiModel(value = "Orderdetail对象", description = "")
public class Orderdetail implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer orderId;

    private String orderThing;

    private LocalDateTime orderDate;

    private Integer sum;
    private  Integer userId;
    private String payState;
    private String tradeNo;

}
