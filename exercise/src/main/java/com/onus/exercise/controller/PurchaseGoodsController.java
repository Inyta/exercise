package com.onus.exercise.controller;

import com.onus.exercise.entity.dto.PurchaseGoodsDTO;
import com.onus.exercise.entity.model.Result;
import com.onus.exercise.service.PurchaseGoodsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseGoodsController {

  private final PurchaseGoodsService purchaseGoodsService;

  public PurchaseGoodsController(PurchaseGoodsService purchaseGoodsService) {
    this.purchaseGoodsService = purchaseGoodsService;
  }

  @PostMapping("/add")
  public Result<Void> add(@RequestBody PurchaseGoodsDTO dto) {
    purchaseGoodsService.add(dto.getPurchaseGoodsList());
    return Result.success();
  }
}
