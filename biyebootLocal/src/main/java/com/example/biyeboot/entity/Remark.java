package com.example.biyeboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
 * @since 2023-04-24
 */
@Getter
@Setter
@TableName("sys_remark")
@ApiModel(value = "Remark对象", description = "")
public class Remark implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String id;

    private String headImg;

    private String userComment;

    private LocalDateTime time;

    private Integer commentNum;
    private String userlike;

    @TableField(exist = false)
    private ArrayList<Replay> reply=new ArrayList<>();


}
