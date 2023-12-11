package com.example.biyeboot.mapper;

import com.example.biyeboot.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jojo
 * @since 2023-03-07
 */
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("SELECT * FROM menu\n" +
            "WHERE parent is null\n")
    List<Menu> getAllParent();
    @Select("SELECT * FROM menu\n" +
            "WHERE parent = #{parent}\n")
    List<Menu> getChildrenMenu(String parent);
    @Select("SELECT * FROM sys_costmmenu\n" +
            "WHERE parent is null\n")
    List<Menu> getAllCostemMenuParent();
    @Select("SELECT * FROM sys_costmmenu\n" +
            "WHERE parent = #{parent}\n")
    List<Menu> getCostemMenuChildrenMenu(String parent);
}
