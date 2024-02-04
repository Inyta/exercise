package com.onus.exercise.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_menu")
public class Menu {

    private Long id;

    private String name;

    private String path;

    private String parentId;


}
