package com.onus.exercise.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onus.exercise.entity.po.PurchaseGoods;
import com.onus.exercise.mapper.PurchaseGoodsMapper;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PurchaseGoodsService extends ServiceImpl<PurchaseGoodsMapper, PurchaseGoods> {

  public void add(List<PurchaseGoods> goodsList) {
    this.saveBatch(goodsList);
  }

}
