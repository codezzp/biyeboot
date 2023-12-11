package com.example.biyeboot.controller;


import com.example.biyeboot.controller.dto.RefundDetail;
import com.example.biyeboot.entity.Refuse;
import com.example.biyeboot.mapper.RefuseMapper;
import com.example.biyeboot.service.IRefundService;
import com.example.biyeboot.service.IRefuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jojo
 * @since 2023-05-22
 */
@Controller
@RequestMapping("/refuse")
public class RefuseController {
    @Autowired
    IRefuseService refuseService;
    @Autowired
    RefuseMapper refuseMapper;
    @PostMapping("/insertRefuse")
    public void insertRefuse(@RequestBody Refuse refuse){
        System.out.println(refuse);
        refuseMapper.insertRefuse(refuse);
        refuseMapper.alterStepState(refuse.getOrderId());
        refuseMapper.alterRefundState(refuse.getOrderId());
    }


}
