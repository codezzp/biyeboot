package com.example.biyeboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.biyeboot.controller.dto.UserDTO;
import com.example.biyeboot.entity.JsonResult;
import com.example.biyeboot.entity.User;
import com.example.biyeboot.mapper.UserMapper;
import com.example.biyeboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.biyeboot.utils.TokenUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jojo
 * @since 2023-03-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public JsonResult<UserDTO> login(UserDTO userDTO) {
        QueryWrapper<User> userQueryWrapper =new QueryWrapper<>();
        userQueryWrapper.eq("username",userDTO.getUsername());
        userQueryWrapper.eq("password",userDTO.getPassword());
        User one = getOne(userQueryWrapper);
        BeanUtil.copyProperties(one,userDTO,true);
        if(one!=null){
            //校验完成后，生成token和id发给前端
            userDTO.setToken(TokenUtils.genToken(one.getId().toString(),one.getPassword()));
            return new JsonResult<UserDTO>(200,userDTO);
        }else {
            return new JsonResult<>(501,"用户名或密码错误");
        }
    }

    @Override
    public JsonResult<User> register(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        QueryWrapper<User> usernameQueryWrapper = new QueryWrapper<>();
        usernameQueryWrapper.eq("username",username);
        List<User> userList = list(usernameQueryWrapper);
        if (userList.size()==0){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            save(user);
            return new JsonResult<>(200,"添加成功");
        }
        else {
            return new JsonResult<>(501,"用户名已存在");
        }

    }


}
