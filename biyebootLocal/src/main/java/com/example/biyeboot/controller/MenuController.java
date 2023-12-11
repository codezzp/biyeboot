package com.example.biyeboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.biyeboot.entity.Menu;
import com.example.biyeboot.mapper.MenuMapper;
import com.example.biyeboot.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jojo
 * @since 2023-03-07
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    IMenuService menuService;
    @GetMapping
    public List<Menu> getAllMenu(){
        List<Menu> allParent = menuMapper.getAllParent();
        for (Menu parent:allParent){
            List<Menu> childrenMenu = menuMapper.getChildrenMenu(parent.getId());
            parent.setChildren(childrenMenu);
        }
        return allParent;
    }
    @GetMapping("/costemmenu")
    public List<Menu> getAllCostumerMenu(){
        List<Menu> allParent = menuMapper.getAllCostemMenuParent();
        for (Menu parent:allParent){
            List<Menu> childrenMenu = menuMapper.getCostemMenuChildrenMenu(parent.getId());
            parent.setChildren(childrenMenu);
        }
        return allParent;
    }
}
