package com.onus.exercise.controller;

import com.onus.exercise.service.GenerateService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generate")
public class GenerateController {

  @Autowired
  private GenerateService generateService;

  @GetMapping("/pdf")
  public void generatePdf(@RequestParam Map<String, String> map) {
    generateService.generate(map);
  }

}
