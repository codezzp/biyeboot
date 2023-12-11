package com.example.biyeboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-03-16
 */
@Getter
@Setter
  @TableName("sys_takeout")
@ApiModel(value = "Takeout对象", description = "")
public class Takeout implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer orderId;

    private Integer userId;

      @ApiModelProperty("配送状态")
      private Integer state;

    private String address;

    private String nickName;


}
