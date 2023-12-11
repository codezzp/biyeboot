package com.example.biyeboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-03-03
 */
@Getter
@Setter
  @ApiModel(value = "Merch对象", description = "")
public class Merch implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("餐品id")
        @TableId(value = "merchID", type = IdType.AUTO)
      private Integer merchID;

      @ApiModelProperty("餐品名称")
      private String fullname;

      @ApiModelProperty("餐品种类")
      private String kind;

      @ApiModelProperty("餐品单价")
      private Long unitCost;

      @ApiModelProperty("餐品图片url")
      private String merchAvatar;

      @ApiModelProperty("餐品配料")
      private String chargeMixture;

    private LocalDateTime date;

      @ApiModelProperty("餐品详情")
      private String detail;
      private Integer num;


}
