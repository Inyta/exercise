package com.onus.exercise.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_goods_type_relation")
public class GoodsTypeRelation {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long goodsId;

    private Long typeId;
}
