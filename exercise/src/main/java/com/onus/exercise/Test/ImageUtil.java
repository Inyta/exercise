package com.onus.exercise.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageUtil {

    public static boolean merge(String[] imgs, String type, String mergePic) {
      int dstHeight = 0;
      int dstWidth = 0;
      // 获取需要拼接的图片长度
      int len = imgs.length;
      // 判断长度是否大于0
      if (len < 1) {
        return false;
      }
      File[] file = new File[len];
      BufferedImage[] images = new BufferedImage[len];
      int[][] ImageArrays = new int[len][];
      for (int i = 0; i < len; i++) {
        try {
          file[i] = new File(imgs[i]);
          images[i] = ImageIO.read(file[i]);
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }

        int width = images[i].getWidth();
        int height = images[i].getHeight();

        // 从图片中读取RGB 像素
        ImageArrays[i] = new int[width * height];
        ImageArrays[i] = images[i].getRGB(0, 0, width, height, ImageArrays[i], 0, width);

        // 计算合并的宽度和高度
        dstWidth = dstWidth > width ? dstWidth : width;
        dstHeight += height;
      }

      // 合成图片像素
      System.out.println("宽度:" + dstWidth);
      System.out.println("高度:" + dstHeight);

      if (dstHeight < 1) {
        System.out.println("dstHeight < 1");
        return false;
      }
      // 生成新图片
      try {
        BufferedImage imageNew = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_RGB);
        int width_i = 0;
        int height_i = 0;
        for (int i = 0; i < images.length; i++) {
          int width = images[i].getWidth();
          int height = images[i].getHeight();
          imageNew.setRGB(0, height_i, width, height, ImageArrays[i], 0, width);
          height_i += height;
        }

        File outFile = new File(mergePic);
        // 写图片，输出到硬盘
        ImageIO.write(imageNew, type, outFile);
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      return true;
    }
  public static void main(String[] args) {
    String saveFile = "D:avatar2.png";
    String images[] = {"D:pathwire.png", "D:pathwire.png","D:pathwire.png","D:pathwire.png","D:pathwire.png","D:pathwire.png"};
    ImageUtil.merge(images, "png", saveFile);
  }
}
