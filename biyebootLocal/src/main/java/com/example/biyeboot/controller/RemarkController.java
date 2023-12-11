package com.example.biyeboot.controller;


import com.example.biyeboot.entity.JsonResult;
import com.example.biyeboot.entity.Remark;
import com.example.biyeboot.mapper.RemarkMapper;
import com.example.biyeboot.service.IRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jojo
 * @since 2023-04-24
 */
@RestController
@RequestMapping("/remark")
public class RemarkController {
    @Autowired
    IRemarkService remarkService;
    @PostMapping("/save")
    public void saveRemark(@RequestBody  Remark remark){
        System.out.println(remark);
        remarkService.save(remark);
    }
    @GetMapping("/genRemark")
    public JsonResult<ArrayList<Remark>> genRemark(){
        ArrayList<Remark> remarks = remarkService.genRemarks();
        return new JsonResult<>(200, remarks);
    }
}
