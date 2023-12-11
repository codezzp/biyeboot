package com.example.biyeboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.biyeboot.entity.Orderdetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jojo
 * @since 2023-03-09
 */
public interface IOrderdetailService extends IService<Orderdetail> {
    public Page<Orderdetail> selectByTimes(Integer pageNum,Integer pageSize,Integer flag);
    public Page<Orderdetail> selectMyOrderByTimes(Integer pageNum,Integer pageSize,Integer flag,Integer userId);
    public int[] getLast7daysSum();
    public List<Integer> getLast7daysSum1();
    public List<String> getDateList();
}
