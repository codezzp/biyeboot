package com.example.biyeboot.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.biyeboot.entity.JsonResult;
import com.example.biyeboot.mapper.MerchMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.biyeboot.service.IMerchService;
import com.example.biyeboot.entity.Merch;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jojo
 * @since 2023-03-03
 */
@RestController
@RequestMapping("/merch")
public class MerchController {

    @Autowired
    private IMerchService merchService;
    @Autowired
    private MerchMapper merchMapper;
    //新增或更新
    @PostMapping
    public boolean save(@RequestBody Merch merch){
        return merchService.saveOrUpdate(merch);
    }
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return merchService.removeById(id);
    }
    @GetMapping
    public List<Merch> findAll() {
        return merchService.list();
    }
    @GetMapping("/{id}")
    public Merch findOne(@PathVariable Integer id) {
        return merchService.getById(id);
    }
    @GetMapping("/page")
    public Page<Merch> findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize,@RequestParam(defaultValue = "") String merchKind) {
        QueryWrapper<Merch> merchQueryWrapper = new QueryWrapper<>();
        if(!"".equals(merchKind) ) {
            merchQueryWrapper.eq("kind", merchKind);
        }
        merchQueryWrapper.orderByDesc("num");
       return merchService.page(new Page<>(pageNum,pageSize),merchQueryWrapper);
    }
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return merchService.removeBatchByIds(ids);
    }
    @GetMapping("/getMerchKind")
    public JsonResult<List<String>> getMerchKind(){
        List<String> merchKinds=merchMapper.getMerchKind();
        return new JsonResult<>(200,merchKinds);
    }
    @GetMapping("/test")
    public void test(){
//        LambdaQueryWrapper<Merch> merchLambdaQueryWrapper = new LambdaQueryWrapper<Merch>();
//        merchLambdaQueryWrapper.select(Merch::getChargeMixture,Merch::getMerchID);
//        merchLambdaQueryWrapper.like(Merch::getChargeMixture,"黄油");
//        List<Merch> merches = merchMapper.selectList(merchLambdaQueryWrapper);
//        for(Merch m:merches){
//            System.out.println(m.getMerchID());
//            System.out.println(m.getChargeMixture());
//        }
        List<Merch> merches = merchMapper.searchByChargeMixture();
    }


}

