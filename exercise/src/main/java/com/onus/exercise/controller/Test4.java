package com.onus.exercise.controller;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.itextpdf.kernel.pdf.canvas.parser.listener.RegexBasedLocationExtractionStrategy;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test4 {

  private static Logger logger = LoggerFactory.getLogger(Test4.class);

  public static void main(String[] args) throws Exception {
    //通过指定pdf文件名，指定关键字，和指定的pdf文件的待处理页数做参数
    getKeyWordsAndInsertImg("D:\\模板.pdf", "D:\\1.png", "二维码", 80f, 80f, 0f, 0f);

  }


  public static String encodeImageToBase64(String imagePath) {
    try {
      File imageFile = new File(imagePath);
      byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
      String base64EncodedString = Base64.getEncoder().encodeToString(imageBytes);
      return base64EncodedString;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
  /**
   * 得到关键字位置插入图片
   *
   * @param sourcePath 源文件
   * @param imagePath  图片位置
   * @param KEY_WORD   关键字
   * @param width      图片宽度
   * @param height     图片高度
   * @param x          相对关键字平移x轴距离
   * @param y          相对关键字平移y轴距离
   */
  public static boolean getKeyWordsAndInsertImg(String sourcePath, String imagePath,
      String KEY_WORD, float width, float height, float x, float y) {
    try {
      //得到需要插入的图片
      String imagePath1 = "D://1.png"; // 替换为你的图片路径
      String base64String = encodeImageToBase64(imagePath1);
      //核心思路为对PdfDocument对象采用某种Strategy，这里使用RegexBasedLocationExtractionStrategy
      byte[] imageBytes = Base64.getDecoder().decode(base64String);
      ImageData imageData = ImageDataFactory.create(imageBytes);
      PdfReader pr = new PdfReader(sourcePath);

      //临时路径
      Date date = new Date();
      String tempPath = sourcePath.replace(".pdf", "_" + date.getTime() + ".pdf");
      //生成新的PDF文件
      PdfDocument pd = new PdfDocument(pr, new PdfWriter(tempPath));
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
            System.out.println(item.getText());
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
        logger.info("图片插入成功！");
      } else {
        logger.error("图片插入失败！");
        return false;
      }
//
//      File oldfile = new File(sourcePath);
//      oldfile.delete();
//      boolean is = new File(tempPath).renameTo(new File(sourcePath));
//      if (is) {
//        logger.info("文件重命名成功！");
//      } else {
//        logger.error("文件重命名失败！");
//        return false;
//      }


    } catch (Exception e) {
      e.printStackTrace();
      logger.error("读取文件插入图片失败！" + e.getMessage());
      return false;
    }

    return true;
  }
}
