package com.onus.exercise.controller;

import com.onus.exercise.service.GenerateService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test5 {
  public static void main(String[] args) {
    String excelFilePath = "D://jing/xu.xlsx"; // 请替换为你的 Excel 文件路径

    try (FileInputStream fis = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(fis)) {

      Sheet sheet = workbook.getSheetAt(0); // 读取第一个工作表

      // 读取第一行（参数/列名）
      Row headerRow = sheet.getRow(0);
      List<String> headers = new ArrayList<>();
      for (Cell cell : headerRow) {
        headers.add(cell.getStringCellValue());
      }

      // 读取数据行（从第二行开始）
      List<Map<String, String>> data = new ArrayList<>();
      for (int i = 1; i <= sheet.getLastRowNum(); i++) {
        Row row = sheet.getRow(i);
        Map<String, String> rowData = new HashMap<>();
        for (int j = 0; j < headers.size(); j++) {
          Cell cell = row.getCell(j);
          String cellValue = "";
          if (cell != null) {
            switch (cell.getCellType()) {
              case STRING:
                cellValue = cell.getStringCellValue();
                break;
              case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                  cellValue = cell.getDateCellValue().toString();
                } else {
                  cellValue = String.valueOf(cell.getNumericCellValue());
                }
                break;
              case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
              case FORMULA:
                cellValue = cell.getCellFormula();
                break;
              default:
                break;
            }
          }
          rowData.put(headers.get(j), cellValue);
        }
        data.add(rowData);
      }

      // 打印数据
      for (Map<String, String> row : data) {
        System.out.println(row);
        GenerateService.generate(row);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
