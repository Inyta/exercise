package com.onus.exercise.service;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.itextpdf.kernel.pdf.canvas.parser.listener.RegexBasedLocationExtractionStrategy;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.texts.PdfTextReplacer;
import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GenerateService {


//  public static void main(String[] args) {
//    Map<String, String> map = new HashMap<>();
//    map.put("$CarCode", "252222413006777");
//    map.put("$ElecCode", "DSAHIDASHDBK1323123");
//    map.put("$color", "蓝/白");
//    generate(map);
//  }


  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Map<String, String> map =new HashMap<>();
    // 获取用户输入的车牌号、模板PDF路径等信息
    System.out.print("请输入车架: ");
    String carCode = scanner.nextLine();
    map.put("$CarCode", carCode); //

    // 用户可以输入多个替换键值对
    System.out.print("请输入车机码:");
    String keyValues = scanner.nextLine();
    map.put("$ElecCode", keyValues); //
    System.out.print("请输入颜色:");
    String color = scanner.nextLine();
    map.put("$color", color); //
    try {
      generate(map);  // 调用生成方法
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      scanner.close();
    }
  }

  public static byte[] generateQRCodeAsBase64(String code) {
    QrConfig config = new QrConfig(300, 300);
// 设置边距，既二维码和背景之间的边距
    config.setMargin(1);
//    QrCodeUtil.generate("http://app.cclc.cn/OtherHtml/ElectricBicycle.aspx?vinCode=" + code, config,
//        FileUtil.file("D://QRCODE.png"));
    byte[] bytes = QrCodeUtil.generatePng(
        "http://app.cclc.cn/OtherHtml/ElectricBicycle.aspx?vinCode=" + code, config);
    return bytes;
  }

  public static String generate(Map<String, String> map) {
    String carCode = map.get("$CarCode");
    //生成二维码
    byte[] bytes = generateQRCodeAsBase64(carCode);
    Date date = new Date();
    boolean insertImg = getKeyWordsAndInsertImg(date, "D://jing/模板.pdf", bytes, "二维码", 80f, 80f, 0f,
        0f);
    if (insertImg) {
      replaceText(map, date);
    }
    String sourcePath = "D://jing/模板_" + date.getTime() + ".pdf";
    return sourcePath;
  }

  public static String replaceText(Map<String, String> map, Date date) {
    // 创建PdfDocument类的实例
    PdfDocument pdf = new PdfDocument();
    // 加载PDF文件
    String sourcePath = "D://jing/模板_" + date.getTime() + ".pdf";
    pdf.loadFromFile(sourcePath);
    // 遍历PDF文件的页面
    for (PdfPageBase page : (Iterable<? extends PdfPageBase>) pdf.getPages()) {
      // 创建PdfTextReplacer类的实例，并将当前页面对象作为参数传递至该类的构造函数
      PdfTextReplacer textReplacer = new PdfTextReplacer(page);
      // 将当前页面上的特定文本的所有实例替换为新文本
      map.forEach((k, v) -> {
        textReplacer.replaceAllText(k, v);
      });
      log.info("文本修改成功");
    }

    // 将结果文件保存为一个新的PDF
    String filePath = "D://v1_" + date.getTime() + ".pdf";
    pdf.saveToFile(filePath);
    File oldfile = new File(sourcePath);
    oldfile.delete();
    new File(filePath).renameTo(new File(sourcePath));
    pdf.close();
    return filePath;
  }

  public static boolean getKeyWordsAndInsertImg(Date date, String sourcePath, byte[] imageBytes,
      String KEY_WORD, float width, float height, float x, float y) {
    try {
      ImageData imageData = ImageDataFactory.create(imageBytes);
      PdfReader pr = new PdfReader(sourcePath);
      //临时路径
      String tempPath = sourcePath.replace(".pdf", "_" + date.getTime() + ".pdf");
      //生成新的PDF文件
      com.itextpdf.kernel.pdf.PdfDocument pd = new com.itextpdf.kernel.pdf.PdfDocument(pr,
          new PdfWriter(tempPath));
      Document document = new Document(pd);
      int pageCount = pd.getNumberOfPages();
      boolean insertFlag = false;
      //遍历每一页
      for (int i = 1; i <= pageCount; i++) {
        PdfPage page = pd.getPage(i);
        //查找页中的匹配文字，并定位到位置。
        RegexBasedLocationExtractionStrategy strategy = new RegexBasedLocationExtractionStrategy(
            KEY_WORD);
        PdfCanvasProcessor processor = new PdfCanvasProcessor(strategy);
        processor.processPageContent(page);
        Collection<IPdfTextLocation> resultantLocations = strategy.getResultantLocations();

        //自定义结果处理
        if (!resultantLocations.isEmpty()) {
          for (IPdfTextLocation item : resultantLocations) {
            Rectangle boundRectangle = item.getRectangle();
            System.out.println(
                "关键字“" + KEY_WORD + "” 的坐标为 x: " + boundRectangle.getX() + "  ,y: "
                    + boundRectangle.getY());
            Image image = new Image(imageData).scaleAbsolute(width, height)
                .setFixedPosition(i, boundRectangle.getRight() + x + 80,
                    boundRectangle.getBottom() + y - 70);
            document.add(image);
            insertFlag = true;
            break;
          }
        }
      }
      document.close();
      pr.close();
      pd.close();
      if (insertFlag) {
        log.info("图片插入成功！");
      } else {
        log.error("图片插入失败！");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      log.error("读取文件插入图片失败！" + e.getMessage());
      return false;
    }
    return true;
  }
}
