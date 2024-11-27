package com.onus.exercise.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class Test {

  public static void main(String[] args) {
    Date date = new Date();
    String templatePath = "D://模板.docx";
    String outputPath = "D://模板" + date.getTime() + ".docx";
    Map<String, String> replacements = new HashMap<>();
    replacements.put("{{$code1}}", "5328592375723");
    replacements.put("{{$code2}}", "DASJKDLJA12312731982");
    replacements.put("{{$color}}", "黄/绿");

    try (FileInputStream fis = new FileInputStream(templatePath);
        XWPFDocument document = new XWPFDocument(fis)) {

      // 遍历所有段落
      for (XWPFParagraph paragraph : document.getParagraphs()) {
        for (XWPFRun run : paragraph.getRuns()) {
          String text = run.getText(0);
          if (text != null) {
            for (Map.Entry<String, String> entry : replacements.entrySet()) {
              text = text.replace(entry.getKey(), entry.getValue());
            }
            run.setText(text, 0);
          }
        }
      }

      // 处理表格中的文本
      for (XWPFTable table : document.getTables()) {
        for (XWPFTableRow row : table.getRows()) {
          for (XWPFTableCell cell : row.getTableCells()) {
            for (XWPFParagraph paragraphInCell : cell.getParagraphs()) {
              for (XWPFRun run : paragraphInCell.getRuns()) {
                String text = run.getText(0);
                if (text != null) {
                  for (Map.Entry<String, String> entry : replacements.entrySet()) {
                    text = text.replace(entry.getKey(), entry.getValue());
                  }
                  run.setText(text, 0);
                }
              }
            }
          }
        }
      }

      // 将修改后的文档写入文件
      try (FileOutputStream fos = new FileOutputStream(outputPath)) {
        document.write(fos);
      }

      System.out.println("Template filling completed successfully.");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
