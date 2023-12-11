package com.example.biyeboot.mapper;

import com.example.biyeboot.entity.Replay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jojo
 * @since 2023-04-24
 */
public interface ReplayMapper extends BaseMapper<Replay> {
    @Select("select * from sys_replay")
    ArrayList<Replay> findAll();
}
