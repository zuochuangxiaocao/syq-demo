package com.syq.demo.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.syq.demo.sharding.dto.CouponBirthdayDto;
import com.syq.demo.sharding.model.CouponBirthday;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponBirthdayMapper extends BaseMapper<CouponBirthday> {

    IPage<CouponBirthday> selectPageVo(IPage<CouponBirthday> page, Integer id);

    IPage<CouponBirthdayDto> selectMyPage(IPage page, Integer id);

}
