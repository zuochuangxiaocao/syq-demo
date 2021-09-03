package com.syq.demo.sharding.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value="t_order")
public class Order {

    @TableId(value = "id",type = IdType.AUTO)
    private long id;

    private Integer storeId;

    private Date createTime;

    private String  name;

}
