package com.onus.exercise.Test;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtils;

public class ImageTest {

  public static void main(String[] args) {
    double[][] data = new double[][]{
        {1108.045, 694.845, 920.1, 1437.315, 78.12, 17.1, 1070.27, 668.705, 746.85, 948.26,
            1151.845, 418.045, 83, 975.635, 997.7, 375.4, 435.69, 760.69, 286.645, 345.45, 938.845,
            136.81, 701.19, 740.99, 359.76, 148.68, 0, 938.975, 1030.505, 1346.015},
        {222.32, 375.135, 372.57, 228.565, 18.9, 0, 227.285, 166.62, 236.155, 248.23, 800.655,
            62.225, 0, 590.61, 353.365, 500.66, 359.625, 240.23, 135.48, 0, 479.085, 123.05, 236.76,
            181.125, 133.485, 65.405, 0, 797.275, 573.495, 199.45},

        {851.415, 728.515, 839.475, 1018.365, 486.165, 0, 1429.66, 906.105, 916.565, 724.02, 767.53,
            446.105, 0, 1110.04, 1063.87, 763.68, 962.725, 810.715, 255.8, 0, 1065.74, 607.26,
            585.035, 655.665, 160.215, 227.85, 0, 1244.375, 848.74, 1171.785},
        {1121.18, 1034.575, 850.885, 1247.615, 391.59, 0, 1153.875, 920.41, 1716.195, 1475.205,
            1255.135, 77.1, 0, 1535.34, 1185.88, 1014.115, 811.65, 1197.17, 58.8, 0, 2325.54,
            1420.04, 1396.27, 1422.125, 1208.675, 66.78, 0, 2240.05, 1273.375, 1344.6},
        {1126.21, 1496.145, 1363.28, 1897.925, 168, 262.42, 621.27, 917.67, 422.165, 482.62, 198.72,
            99.7, 70, 643.56, 1385.725, 336.74, 445.81, 329.205, 77.635, 57.5, 339.63, 420.17,
            347.3, 147.31, 213.55, 0, 0, 1067.58, 1552.835, 831.57}};
    String[] rowKeys = {"南京", "苏州", "无锡", "常州", "镇江"};
    String[] columnKeys = {"8月4日", "8月5日", "8月6日", "8月7日", "8月8日", "8月9日", "8月10日",
        "8月11日", "8月12日", "8月13日", "8月14日", "8月15日", "8月16日", "8月17日", "8月18日",
        "8月19日", "8月20日", "8月21日", "8月22日", "8月23日", "8月24日", "8月25日", "8月26日",
        "8月27日", "8月28日", "8月29日", "8月30日", "8月31日", "9月1日", "9月2日"};
    CategoryDataset dataset = getBarData(data, rowKeys, columnKeys);
    createWireChar("苏南户用申请容量", "", "", dataset, "wire.png");
  }

  /**
   * 生成折线图
   */

  // 折线图 数据集
  public static CategoryDataset getBarData(double[][] data, String[] rowKeys, String[] columnKeys) {
    return DatasetUtils.createCategoryDataset(rowKeys, columnKeys, data);
  }

  /**
   * 折线图
   *
   * @param chartTitle
   * @param x
   * @param y
   * @param xyDataset
   * @param charName
   * @return
   */
  public static String createWireChar(String chartTitle, String x, String y,
      CategoryDataset xyDataset, String charName) {

    // 创建折线图
    JFreeChart chart = ChartFactory.createLineChart(chartTitle, // 图表标题
        x, // X轴标签
        y, // Y轴标签
        xyDataset, // 数据集
        PlotOrientation.VERTICAL, // 图表方向
        true, // 是否显示图例
        true, // 是否生成工具
        false // 是否生成URL链接
    );

    //设置图表背景色为白色
    chart.setBackgroundPaint(Color.WHITE);

    //设置图表边框不可见
    chart.setBorderVisible(false);

    //获取图表绘图区
    CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
    //图表区的颜色
//    categoryplot.setBackgroundPaint(Color.WHITE);
    //设置图例项的字体
    LegendTitle legend = chart.getLegend();
    if (legend != null) {
      Font legendFont = new Font("微软雅黑", Font.PLAIN, 12); // 使用支持中文的字体
      // 设置图例项的字体
      legend.setItemFont(legendFont);
    }

    //设置轴标签和轴标题的字体
    Font labelFont = new Font("微软雅黑", Font.PLAIN, 10);
    CategoryAxis domainAxis = categoryplot.getDomainAxis(); // 获取X轴
    //设置X轴标题字体
    domainAxis.setLabelFont(labelFont);
    domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45); // 横轴上的
    //设置X轴刻度标签字体
    domainAxis.setTickLabelFont(labelFont);

    //获取Y轴（注意变量名的一致性）
    NumberAxis numberAxis = (NumberAxis) categoryplot.getRangeAxis();
    //设置Y轴刻度单位
    numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    //设置Y轴自动范围包括0
    numberAxis.setAutoRangeIncludesZero(true);

    //设置图标题的字体
    Font titleFont = new Font("微软雅黑", Font.BOLD, 25);
    TextTitle chartTitleText = new TextTitle(chartTitle);
    //设置图表标题的字体
    chartTitleText.setFont(titleFont);
    //设置图表的标题
    chart.setTitle(chartTitleText);

    ValueAxis valueAxis = categoryplot.getRangeAxis();
    //是否自动设置数据轴数据范围
    valueAxis.setAutoRange(true);
    valueAxis.setVisible(true);

    // 获得renderer
    LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();
    // series 点（即数据点）可见
    lineandshaperenderer.setDefaultShapesVisible(true);
    // series 点（即数据点）间有连线可见
    lineandshaperenderer.setDefaultLinesVisible(true);
    //series 数据值可见
    lineandshaperenderer.setDefaultItemLabelsVisible(false);
    lineandshaperenderer.setDefaultItemLabelGenerator(
        new StandardCategoryItemLabelGenerator("{2}", NumberFormat.getNumberInstance()));
    FileOutputStream fos_jpg = null;
    try {
      isChartPathExist("D:/temp");
      String chartName = "path" + charName;
      fos_jpg = new FileOutputStream(chartName);

      // 将报表保存为png文件
      ChartUtils.writeChartAsPNG(fos_jpg, chart, 1100, 872);

      return chartName;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      try {
        if (fos_jpg != null) {
          fos_jpg.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 判断文件夹是否存在，如果不存在则新建
   *
   * @param chartPath
   */
  private static void isChartPathExist(String chartPath) {
    File file = new File(chartPath);
    if (!file.exists()) {
      file.mkdirs();
    }
  }
}
