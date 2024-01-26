package com.onus.exercise.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zhang
 */
@RestController
public class GPCPowerController {

    @GetMapping("/test")
    public void test(@RequestParam("city") String city,
                     @RequestParam("maintOrg") String maintOrg,
                     @RequestParam("statDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                     @RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                     @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize){

    }

}
