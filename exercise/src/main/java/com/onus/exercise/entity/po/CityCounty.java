package com.onus.exercise.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_city_county_dic")
public class CityCounty {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String city;

    private String cityName;

    private String county;

    private String countyName;

}
