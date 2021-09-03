package com.syq.demo.sharding;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.syq.demo.sharding.mapper.CouponBirthdayMapper;
import com.syq.demo.sharding.mapper.OrderMapper;
import com.syq.demo.sharding.model.CouponBirthday;
import com.syq.demo.sharding.model.Order;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class ShardingApplicationTests {

    @Autowired
    OrderMapper orderMapper;

    @Test
    void contextLoads() {
    }


    @Test
    public void testSelect() {
        Order order = new Order();
        order.setName("1111");
        order.setStoreId(1);
        order.setCreateTime(new Date());
        orderMapper.insert(order);

        order = new Order();
        order.setName("2222");
        order.setStoreId(2);
        order.setCreateTime(new Date());
        orderMapper.insert(order);

    }

}
