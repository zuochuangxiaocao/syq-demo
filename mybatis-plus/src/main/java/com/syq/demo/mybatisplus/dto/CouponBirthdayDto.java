package com.syq.demo.mybatisplus.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
public class CouponBirthdayDto {

    private Integer id;

    private Integer memberId;

    private Integer storeId;

    private Date createTime;

    private String storeName;
}
