package com.example.biyeboot.controller;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.biyeboot.controller.dto.UserDTO;
import com.example.biyeboot.entity.JsonResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.biyeboot.service.IUserService;
import com.example.biyeboot.entity.User;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jojo
 * @since 2023-03-02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    //新增或更新
    @PostMapping
    public boolean save(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }
    //通过id删除
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }
    //查询所有
    @GetMapping
    public List<User> findAll() {
        return userService.list();
    }
    //通过id查询单个
    @GetMapping("/{id}")
    public User findOne(@PathVariable Integer id) {
        return userService.getById(id);
    }
    //分页查询
    @GetMapping("/page")
    public Page<User> findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize,@RequestParam(defaultValue = "") String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username) ){
            queryWrapper.like("username", username);
        }
        return userService.page(new Page<>(pageNum, pageSize),queryWrapper);
    }
    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return userService.removeBatchByIds(ids);
    }
    //用户登录
    @PostMapping("/login")
    public JsonResult<UserDTO> login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            return new JsonResult<>(501,"用户名密码为空");
        }
        return userService.login(userDTO);
    }
    //用户注册
    @PostMapping("/register")
    public JsonResult<User> register(@RequestBody UserDTO userDTO){
        return userService.register(userDTO);
    }
    @GetMapping ("/getIdByToken/{token}")
    public JsonResult<Integer> getIdByToken(@PathVariable String token){
        int userId;
        try {
            userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        return new JsonResult<>(200,userId);
    }

}

