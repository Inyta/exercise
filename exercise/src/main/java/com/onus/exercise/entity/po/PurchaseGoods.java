package com.onus.exercise.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("t_purchase_goods")
public class PurchaseGoods {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String goodsName;

    private String brand;

    private String purchaseChannel;

    private BigDecimal price;

    private Date purchaseDate;

    private BigDecimal quantity;

    private String unit;

    private Date shelfDate;

    private Integer plannedMeals;

    private Date plannedFinishDate;

    private Integer plannedUserQuantity;
}
