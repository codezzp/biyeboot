package com.example.biyeboot.mapper;

import com.example.biyeboot.entity.Remark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.biyeboot.entity.Replay;
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
public interface RemarkMapper extends BaseMapper<Remark> {
    @Select("select * from sys_remark ")
    ArrayList<Remark> findAll();


}
