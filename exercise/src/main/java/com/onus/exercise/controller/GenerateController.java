package com.onus.exercise.controller;

import com.onus.exercise.service.GenerateService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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
    GenerateService.generate(map);
  }

  @GetMapping("/download")
  public void downloadFile(HttpServletResponse response, @RequestParam Map<String, String> map)
      throws IOException {
    String filePath = GenerateService.generate(map);
    // 文件路径
    File file = new File(filePath);

    // 读取文件内容到输入流
    InputStream inputStream = Files.newInputStream(Paths.get(filePath));

    // 设置响应头
    response.reset();
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition",
        "attachment;filename=" + map.get("$CarCode")+".pdf");
    response.setContentLengthLong(file.length());

    // 将文件内容写入到响应的输出流中
    ServletOutputStream outputStream = response.getOutputStream();
    byte[] buffer = new byte[1024];
    int bytesRead;
    while ((bytesRead = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, bytesRead);
    }

    // 关闭流
    inputStream.close();
    outputStream.flush();
  }
}
