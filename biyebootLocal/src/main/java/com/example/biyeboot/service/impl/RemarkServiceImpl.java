package com.example.biyeboot.service.impl;

import com.example.biyeboot.entity.Remark;
import com.example.biyeboot.entity.Replay;
import com.example.biyeboot.mapper.RemarkMapper;
import com.example.biyeboot.mapper.ReplayMapper;
import com.example.biyeboot.service.IRemarkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.ArrayList;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jojo
 * @since 2023-04-24
 */
@Service
public class RemarkServiceImpl extends ServiceImpl<RemarkMapper, Remark> implements IRemarkService {
    @Autowired
    ReplayMapper replayMapper;
    @Autowired
    RemarkMapper remarkMapper;

    public ArrayList<Remark> genRemarks() {
        ArrayList<Replay> replays = replayMapper.findAll();
        ArrayList<Remark> remarks = remarkMapper.findAll();
        for (Remark remark : remarks) {
            for (Replay replay : replays) {
                if (remark.getId().equals(replay.getToId())){
                    remark.setCommentNum(remark.getCommentNum()+1);
                   remark.getReply().add(replay);
                }
            }
        }

        return remarks;
    }
}
