package com.onus.exercise.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_consumption_goods")
public class ConsumptionGoods {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long purchaseId;

    private Date consumptionDate;

    private BigDecimal eatenQuantity;

    private Integer actualEaterQuantity;

}
