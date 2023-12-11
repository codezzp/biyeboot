package com.example.biyeboot.entity;

import java.io.Serializable;
import java.util.List;

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
 * @since 2023-03-07
 */
@Getter
@Setter
@ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单id")
    private String id;

    @ApiModelProperty("菜单名")
    private String name;

    @ApiModelProperty("权限")
    private String authority;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("前驱节点")
    private String parent;

    private List<Menu> children;


}
