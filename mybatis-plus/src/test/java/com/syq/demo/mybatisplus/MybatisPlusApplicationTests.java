package com.syq.demo.mybatisplus;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.syq.demo.mybatisplus.dto.CouponBirthdayDto;
import com.syq.demo.mybatisplus.mapper.CouponBirthdayMapper;
import com.syq.demo.mybatisplus.model.CouponBirthday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    CouponBirthdayMapper couponBirthdayMapper;

    @Test
    void contextLoads() {
    }


    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        CouponBirthday couponBirthday =  couponBirthdayMapper.selectById(1);
        CouponBirthday c = new CouponBirthday();
        c.setMemberId(1);
        c.setStoreId(1);
        c.setCreateTime(new Date());
        couponBirthdayMapper.insert(c);

        /*QueryWrapper<CouponBirthday> queryWrapper = new QueryWrapper<CouponBirthday>();
        queryWrapper.eq("id",1);
        IPage<CouponBirthday> userPage = new Page<>(1, 10);
        userPage = couponBirthdayMapper.selectPage(userPage,queryWrapper);*/

        IPage userPage = new Page<>(1, 10);

        IPage<CouponBirthdayDto> s = couponBirthdayMapper.selectMyPage(userPage,1);

        System.out.println(s);
    }
}
