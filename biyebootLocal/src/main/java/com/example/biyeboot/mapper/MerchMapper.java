package com.example.biyeboot.mapper;

import com.example.biyeboot.entity.Merch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jojo
 * @since 2023-03-03
 */
public interface MerchMapper extends BaseMapper<Merch> {
    @Select("SELECT DISTINCT kind FROM merch")
    List<String> getMerchKind();
    @Select("SELECT merchID,charge_mixture FROM `merch` where charge_mixture like \"%黄油%\"")
    List<Merch> searchByChargeMixture();
}
