package com.example.biyeboot.service;

import com.example.biyeboot.controller.dto.UserDTO;
import com.example.biyeboot.entity.JsonResult;
import com.example.biyeboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jojo
 * @since 2023-03-02
 */
public interface IUserService extends IService<User> {
    //用户登录
    JsonResult<UserDTO> login(UserDTO userDTO);
    //用户注册
    JsonResult<User> register(UserDTO userDTO);

}
