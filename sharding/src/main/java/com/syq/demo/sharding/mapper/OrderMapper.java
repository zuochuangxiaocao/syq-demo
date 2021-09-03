package com.syq.demo.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syq.demo.sharding.model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
