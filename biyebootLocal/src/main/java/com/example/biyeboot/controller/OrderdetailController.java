package com.example.biyeboot.controller;



import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.biyeboot.controller.dto.ChartData;
import com.example.biyeboot.entity.JsonResult;
import com.example.biyeboot.mapper.OrderdetailMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.example.biyeboot.service.IOrderdetailService;
import com.example.biyeboot.entity.Orderdetail;

import org.springframework.stereotype.Controller;

import javax.xml.soap.Detail;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jojo
 * @since 2023-03-09
 */
@RestController
@RequestMapping("/orderdetail")
public class OrderdetailController {

    @Autowired
    private IOrderdetailService orderdetailService;
    @Autowired
    private OrderdetailMapper orderdetailMapper;
    //新增或更新
    @PostMapping
    public boolean save(@RequestBody Orderdetail orderdetail){
        return orderdetailService.saveOrUpdate(orderdetail);
    }
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return orderdetailMapper.removeById(id);
    }
    @GetMapping
    public List<Orderdetail> findAll() {
        return orderdetailService.list();
    }
    @GetMapping("/{id}")
    public Orderdetail findOne(@PathVariable Integer id) {
        return orderdetailService.getById(id);
    }
    @GetMapping("/page")
    public Page<Orderdetail> findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize ,@RequestParam(defaultValue = "") String orderThing,@RequestParam(defaultValue = "")Integer flag) {
        QueryWrapper<Orderdetail> orderdetailQueryWrapper = new QueryWrapper<>();
        System.out.println(orderThing);
        if(flag!=null) {
           return orderdetailService.selectByTimes(pageNum,pageSize,flag);
        }
        else if(!"".equals(orderThing)) {
            orderdetailQueryWrapper.like("order_thing",orderThing);
            return orderdetailService.page(new Page<>(pageNum, pageSize), orderdetailQueryWrapper);
        }
        else {
            return orderdetailService.page(new Page<>(pageNum,pageSize));
        }
    }
    @GetMapping("/myOrderPage")
    public Page<Orderdetail> findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam String token
    ,@RequestParam(defaultValue = "") String orderThing,@RequestParam(defaultValue = "") Integer flag){
        System.out.println("外围的flag"+flag);
        Integer userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        QueryWrapper<Orderdetail> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("user_id",userId);
        if (flag!=null){
            return orderdetailService.selectMyOrderByTimes(pageNum,pageSize,flag,userId);
        }
        else if(!"".equals(orderThing)){
            objectQueryWrapper.like("order_thing",orderThing);
            return orderdetailService.page(new Page<Orderdetail>(pageNum,pageSize),objectQueryWrapper);
        }
        return orderdetailService.page(new Page<Orderdetail>(pageNum,pageSize),objectQueryWrapper);
    }
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){

        System.out.println(ids);
        return orderdetailMapper.removeBatchByIds(ids);
    }
    @GetMapping("/getDaySumByDates")
    public int[] getDaySumByDate(){
        return orderdetailService.getLast7daysSum();
    }
    @GetMapping("/getChartData")
    public JsonResult<ChartData> getDateList(){
        List<String> dateList= orderdetailService.getDateList();
        List<Integer> last7dateSum=orderdetailService.getLast7daysSum1();
        ChartData chartData = new ChartData();
        chartData.setDateList(dateList);
        chartData.setLast7dateSum(last7dateSum);
        return new JsonResult<>(200,chartData);
    }

//    @GetMapping("/page/selectByTimes")
//    public Page<Orderdetail> selectByTimes(@RequestParam Integer pageNum,
//                                          @RequestParam Integer pageSize,@RequestParam Integer flag ){
//        Page<Orderdetail> orderdetailPage = new Page<>();
//        pageNum=(pageNum-1)*pageSize;
//        List<Orderdetail> orderdetailList= orderdetailMapper.getOrderByInterValDate(pageNum,pageSize,flag);
//        Integer total= orderdetailMapper.getOrderByInterValDateToal(flag);
//        orderdetailPage.setRecords(orderdetailList);
//        orderdetailPage.setTotal(total);
//        return orderdetailPage;
//    }
}

