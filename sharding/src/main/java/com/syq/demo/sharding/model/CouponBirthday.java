package com.syq.demo.sharding.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value="t_coupon_birthday")
public class CouponBirthday {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer memberId;

    private Integer storeId;

    private Date createTime;
}
