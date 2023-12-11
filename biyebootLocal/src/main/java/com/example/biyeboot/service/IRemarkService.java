package com.example.biyeboot.service;

import com.example.biyeboot.entity.Remark;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jojo
 * @since 2023-04-24
 */
public interface IRemarkService extends IService<Remark> {
    ArrayList<Remark> genRemarks();
}
