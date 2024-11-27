package com.onus.exercise.controller;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.texts.PdfTextReplacer;

public class Test3 {

  public static void main(String[] args) {
    // 创建PdfDocument类的实例
    PdfDocument pdf = new PdfDocument();
    // 加载PDF文件
    pdf.loadFromFile("D://模板.pdf");

    // 遍历PDF文件的页面
    for (PdfPageBase page : (Iterable<? extends PdfPageBase>) pdf.getPages()) {
      // 创建PdfTextReplacer类的实例，并将当前页面对象作为参数传递至该类的构造函数
      PdfTextReplacer textReplacer = new PdfTextReplacer(page);
      // 将当前页面上的特定文本的所有实例替换为新文本
      textReplacer.replaceAllText("$CarCode", "5345434121423534");
      textReplacer.replaceAllText("$ElecCode", "SDHAIDASI451341");
      textReplacer.replaceAllText("$color", "蓝/白");
    }

    // 将结果文件保存为一个新的PDF
    pdf.saveToFile("D://替换所有实例.pdf");
    pdf.close();
  }
}
