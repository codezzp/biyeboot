package com.example.biyeboot.service.impl;

import com.example.biyeboot.controller.dto.OrderShow;
import com.example.biyeboot.entity.Ordernum;
import com.example.biyeboot.mapper.OrdernumMapper;
import com.example.biyeboot.service.IOrdernumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jojo
 * @since 2023-03-03
 */
@Service
public class OrdernumServiceImpl extends ServiceImpl<OrdernumMapper, Ordernum> implements IOrdernumService {

}
