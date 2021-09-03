package com.syq.demo.sharding.dto;

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
