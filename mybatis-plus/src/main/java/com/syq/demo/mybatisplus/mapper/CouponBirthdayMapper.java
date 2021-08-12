package com.syq.demo.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.syq.demo.mybatisplus.dto.CouponBirthdayDto;
import com.syq.demo.mybatisplus.model.CouponBirthday;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponBirthdayMapper extends BaseMapper<CouponBirthday> {

    IPage<CouponBirthday> selectPageVo(IPage<CouponBirthday> page, Integer id);

    IPage<CouponBirthdayDto> selectMyPage(IPage page, Integer id);

}
