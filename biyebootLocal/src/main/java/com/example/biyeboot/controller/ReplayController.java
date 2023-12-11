package com.example.biyeboot.controller;


import com.example.biyeboot.entity.Replay;
import com.example.biyeboot.service.IRemarkService;
import com.example.biyeboot.service.IReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jojo
 * @since 2023-04-24
 */
@RestController
@RequestMapping("/replay")
public class ReplayController {
    @Autowired
    IReplayService replayService;
    @PostMapping("/save")
    public void saveReply(@RequestBody Replay replay){
        replayService.save(replay);
    }
}
