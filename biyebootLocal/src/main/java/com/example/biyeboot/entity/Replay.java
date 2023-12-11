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
 * @since 2023-04-24
 */
@Getter
@Setter
@TableName("sys_replay")
@ApiModel(value = "Replay对象", description = "")
public class Replay implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userfrom;

    private String fromId;

    private String fromHeadImg;

    private String toUser;

    private String toId;

    private String userComment;

    private LocalDateTime time;

    private Integer commentNum;

    private String userlike;

    private Integer inputShow;


}
