package com.example.biyeboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.biyeboot.entity.Orderdetail;
import com.example.biyeboot.mapper.OrderdetailMapper;
import com.example.biyeboot.service.IOrderdetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jojo
 * @since 2023-03-09
 */
@Service
public class OrderdetailServiceImpl extends ServiceImpl<OrderdetailMapper, Orderdetail> implements IOrderdetailService {
    @Autowired
    OrderdetailMapper orderdetailMapper;

    @Override
    public Page<Orderdetail> selectByTimes(Integer pageNum, Integer pageSize, Integer flag) {
        Page<Orderdetail> orderdetailPage = new Page<>();
        pageNum = (pageNum - 1) * pageSize;
        if (flag == 0 || flag == 1) {
            List<Orderdetail> orderdetailList = orderdetailMapper.getOrderByDate(pageNum, pageSize, flag);
            Integer total = orderdetailMapper.getOrderByDateToal(flag);
            orderdetailPage.setRecords(orderdetailList);
            orderdetailPage.setTotal(total);
            return orderdetailPage;
        } else {
            List<Orderdetail> orderdetailList = orderdetailMapper.getOrderByInterValDate(pageNum, pageSize, flag);
            Integer total = orderdetailMapper.getOrderByInterValDateToal(flag);
            orderdetailPage.setRecords(orderdetailList);
            orderdetailPage.setTotal(total);
            return orderdetailPage;
        }
    }

    @Override
    public Page<Orderdetail> selectMyOrderByTimes(Integer pageNum, Integer pageSize, Integer flag, Integer userId) {
        System.out.println("内围的flag"+flag);
        Page<Orderdetail> orderdetailPage = new Page<>();
        pageNum = (pageNum - 1) * pageSize;
        if (flag == 0 || flag == 1) {

            List<Orderdetail> orderdetailList = orderdetailMapper.getMyOrderByDate(pageNum, pageSize, flag, userId);
            Integer total = orderdetailMapper.getMyOrderByDateToal(flag, userId);
            orderdetailPage.setRecords(orderdetailList);
            orderdetailPage.setTotal(total);
            return orderdetailPage;
        } else {
            System.out.println(flag);
            List<Orderdetail> orderdetailList = orderdetailMapper.getMyOrderByDates(pageNum, pageSize, flag, userId);
            Integer total = orderdetailMapper.getMyOrderByDatesToal(flag, userId);
            orderdetailPage.setRecords(orderdetailList);
            orderdetailPage.setTotal(total);
            return orderdetailPage;
        }
    }

    @Override
    public int[] getLast7daysSum() {
        int[] arr = new int[7];
        for (int i = 0; i < 7; i++) {
            if (orderdetailMapper.getDaySumByDate(i) == null) {
                arr[i] = 0;
            } else {
                arr[i] = orderdetailMapper.getDaySumByDate(i);
            }
        }
        for (int i = 0; i < 7; i++) {
            System.out.println(arr[i]);
        }
        return arr;
    }

    @Override
    public List<Integer> getLast7daysSum1() {
        ArrayList<Integer> arry = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if (orderdetailMapper.getDaySumByDate(i) == null) {
                arry.add(0);
            } else {
                arry.add(orderdetailMapper.getDaySumByDate(i));
            }
        }
        return arry;

    }

    @Override
    public List<String> getDateList() {
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                cal.add(Calendar.DATE,0);
                Date date = cal.getTime();
                String date01 = simpleDateFormat.format(date);
                dateList.add(date01);
            }
            else {
                cal.add(Calendar.DATE, -1);
                Date date = cal.getTime();
                String date01 = simpleDateFormat.format(date);
                dateList.add(date01);
            }
        }
        return dateList;
    }
}
