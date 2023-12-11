package com.example.biyeboot.controller;



import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.biyeboot.controller.dto.TakeOutDTO;
import com.example.biyeboot.entity.JsonResult;
import com.example.biyeboot.mapper.TakeoutMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.biyeboot.service.ITakeoutService;
import com.example.biyeboot.entity.Takeout;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jojo
 * @since 2023-03-16
 */
@RestController
@RequestMapping("/takeout")
public class TakeoutController {

    @Autowired
    private ITakeoutService takeoutService;
    @Autowired
    private TakeoutMapper takeoutMapper;
    //新增或更新
    @PostMapping
    public boolean save(@RequestBody Takeout takeout){
        return takeoutService.saveOrUpdate(takeout);
    }
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return takeoutService.removeById(id);
    }
    @GetMapping
    public List<Takeout> findAll() {
        return takeoutService.list();
    }
    @GetMapping("/{id}")
    public Takeout findOne(@PathVariable Integer id) {
        return takeoutService.getById(id);
    }
    @GetMapping("/page")
    public Page<TakeOutDTO> findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize,@RequestParam String token) {
        Integer userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        Page<TakeOutDTO> takeOutPage = new Page<>();
        pageNum=(pageNum-1)*pageSize;
        List<TakeOutDTO> allTakeOutByUser = takeoutMapper.getAllTakeOutByUser(userId, pageNum, pageSize);
        Integer allTakeOutByUserToal = takeoutMapper.getAllTakeOutByUserToal(userId);
        takeOutPage.setTotal(allTakeOutByUserToal);
        takeOutPage.setRecords(allTakeOutByUser);
        return takeOutPage;
    }
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return takeoutService.removeBatchByIds(ids);
    }
    @GetMapping("/allpage")
    public Page<TakeOutDTO> getAllTakeOutPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize ,
                                              @RequestParam(defaultValue ="") Integer flag){
        pageNum = (pageNum - 1) * pageSize;
        Page<TakeOutDTO> takeOutDTOPage = new Page<>();
        if (flag==null) {
            takeOutDTOPage.setRecords(takeoutMapper.getAllTakeOut(pageNum, pageSize));
            takeOutDTOPage.setTotal(takeoutMapper.getAllTakeOutTotal());
            return takeOutDTOPage;
        }
        else {
            takeOutDTOPage.setRecords(takeoutMapper.getAllTakeOutNotFinish(pageNum,pageSize));
            takeOutDTOPage.setTotal(takeoutMapper.getAllTakeOutNotFinishTotal());
            return takeOutDTOPage;
        }
    }
    @GetMapping("/changeState")
    public JsonResult<Void> changeState(@RequestParam Integer state,@RequestParam Integer orderId){
        takeoutMapper.changeState(state,orderId);
        return new JsonResult<>(200);
    }
}

