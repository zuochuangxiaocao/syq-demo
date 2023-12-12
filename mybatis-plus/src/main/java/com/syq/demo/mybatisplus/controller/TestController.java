package com.syq.demo.mybatisplus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.syq.demo.mybatisplus.dto.CouponBirthdayDto;
import com.syq.demo.mybatisplus.mapper.CouponBirthdayMapper;
import com.syq.demo.mybatisplus.model.CouponBirthday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    CouponBirthdayMapper couponBirthdayMapper;

    @GetMapping("/create")
    public String create(){
        System.out.println(("----- selectAll method test ------"));
        CouponBirthday couponBirthday =  couponBirthdayMapper.selectById(1);
        CouponBirthday c = new CouponBirthday();
        c.setMemberId(1);
        c.setStoreId(1);
        c.setCreateTime(new Date());
        couponBirthdayMapper.insert(c);
        return c.toString();
    }

    @GetMapping("/find")
    public Object find(){
        IPage userPage = new Page<>(1, 10);
        IPage<CouponBirthdayDto> s = couponBirthdayMapper.selectMyPage(userPage,1);
        return s;
    }



}
