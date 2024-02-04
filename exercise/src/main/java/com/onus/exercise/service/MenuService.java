package com.onus.exercise.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onus.exercise.entity.po.Menu;
import com.onus.exercise.mapper.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> {
}
