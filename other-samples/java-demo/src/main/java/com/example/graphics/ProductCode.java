package com.example.graphics;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;
import org.junit.jupiter.api.Test;

class Device {

  public String deviceNo;
  public String deviceName;
  public String departId;
  public String deviceModel;
  public String installPlace;
  public String purchaseTime;
  public String manufacturer;
  public String deviceId;

  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }

  public String getDeviceName() {
    return deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public String getDepartId() {
    return departId;
  }

  public void setDepartId(String departId) {
    this.departId = departId;
  }

  public String getDeviceModel() {
    return deviceModel;
  }

  public void setDeviceModel(String deviceModel) {
    this.deviceModel = deviceModel;
  }

  public String getInstallPlace() {
    return installPlace;
  }

  public void setInstallPlace(String installPlace) {
    this.installPlace = installPlace;
  }

  public String getPurchaseTime() {
    return purchaseTime;
  }

  public void setPurchaseTime(String purchaseTime) {
    this.purchaseTime = purchaseTime;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }
}

public class ProductCode {


  public static void drawImage(String fileName, int count) {
    try {
      PrintService ps = PrintServiceLookup.lookupDefaultPrintService();

      PrintRequestAttributeSet attribute = new HashPrintRequestAttributeSet();
      attribute.add(OrientationRequested.PORTRAIT);
      attribute.add(new Copies(count));
      attribute.add(PrintQuality.HIGH);

      DocAttributeSet das = new HashDocAttributeSet();
      das.add(new MediaPrintableArea(0, 0, 210, 296, MediaPrintableArea.MM));

      FileInputStream fin = new FileInputStream(fileName);
      Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.PNG, das);
      DocPrintJob job = ps.createPrintJob();
      job.print(doc, attribute);
      fin.close();
    } catch (IOException ie) {
      ie.printStackTrace();
    } catch (PrintException pe) {
      pe.printStackTrace();
    }
  }

  public static void createQrCode2(String fileName, Device device) throws IOException, WriterException {
    File filepath = new File(fileName);
    //判断路径是否存在，如果不存在就创建一个
    if (!filepath.getParentFile().exists()) {
      filepath.getParentFile().mkdirs();
    }

    Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  // 矫错级别
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    //创建比特矩阵(位矩阵)的QR码编码的字符串
    String contents = new String(device.getDeviceId().getBytes("UTF-8"), "ISO-8859-1");

    BitMatrix byteMatrix = qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, 90, 90, hintMap);
    int matrixWidth = byteMatrix.getWidth();
    BufferedImage image = new BufferedImage(matrixWidth - 20, matrixWidth - 20, BufferedImage.TYPE_INT_RGB);
    image.createGraphics();
    Graphics2D graphics = (Graphics2D) image.getGraphics();
    graphics.setColor(Color.LIGHT_GRAY);
    graphics.fillRect(0, 0, matrixWidth, matrixWidth);
    // 使用比特矩阵画并保存图像
    graphics.setColor(Color.BLACK);
    for (int i = 0; i < matrixWidth; i++) {
      for (int j = 0; j < matrixWidth; j++) {
        if (byteMatrix.get(i, j)) {
          graphics.fillRect(i - 10, j - 10, 1, 1);
        }
      }
    }
    image.flush();

    int width1 = 470; // 第二层图片宽
    int height1 = 200;// 第二层图片高
    // 得到图片缓冲区 第二层图片
    BufferedImage bi = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);// INT精确度达到一定,RGB三原色，高度70,宽度150
    bi.createGraphics();
    // 得到它的绘制环境(这张图片的笔)
    Graphics2D g2 = (Graphics2D) bi.getGraphics();
    g2.setColor(Color.LIGHT_GRAY); // 设置背景颜色
    g2.fillRect(0, 0, width1, height1);// 填充整张图片(其实就是设置背景颜色)
    g2.setColor(Color.black);// 设置字体颜色
    g2.setStroke(new BasicStroke(2.0f)); // 边框加粗
    g2.drawRect(1, 1, width1 - 2, height1 - 2); // 画边框就是黑边框

    g2.setStroke(new BasicStroke(1.0f)); // 边框加粗大小为1.0f
    g2.drawLine(0, 40, 470, 40); // 从上到下第二个横线(设备编码下面横线)
    g2.drawLine(0, 80, 470, 80); // 从上到下第三个横线(设备名称下面横线)
    g2.drawLine(0, 120, 470, 120); // 从上到下第四个横线(使用部门下面横线)
    g2.drawLine(0, 160, 390, 160); // 从上到下第5个横线(安装位置下面横线)
    g2.drawLine(0, 200, 390, 200); // 从上到下第6个横线(安装位置下面横线)
    g2.drawLine(390, 120, 390, 200); // 从左到右第二个竖线

    g2.setFont(new Font("宋体", Font.BOLD, 22)); // 设置标题的字体,字号,大小
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 抗锯齿

    g2.setFont(new Font("宋体", Font.BOLD, 22));
    g2.drawString("设备名称：" + device.getDeviceName(), 10, 25);
    g2.drawString("设备编码：" + device.getDeviceNo(), 10, 65);
    g2.drawString("规格型号：" + device.getDeviceModel(), 10, 105);
    g2.drawString("安装位置：" + device.getInstallPlace(), 10, 145);
    g2.drawString("购置日期：" + device.getPurchaseTime().substring(0, 10), 10, 185);

    // 将二维码粘贴到新的面板
    g2.drawImage(image, 395, 125, image.getWidth(), image.getHeight(), null);
    g2.dispose(); // 释放对象
    bi.flush();

    //生成第三层画布
    int width = 476; // 第三层图片宽
    int height = 295;// 第三层图片高
    BufferedImage bi3 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);// INT精确度达到一定,RGB三原色，高度70,宽度150
    bi3.createGraphics();
    // 得到它的绘制环境(这张图片的笔)
    Graphics2D g3 = (Graphics2D) bi3.getGraphics();
    g3.setColor(Color.WHITE); // 设置背景颜色
    g3.fillRect(0, 0, width, height);// 填充整张图片(其实就是设置背景颜色)

    //将logo图粘贴到新画布 这里是springboot获取静态文件地址，也可以用绝对路径获取图片文件地址
    //g3.drawImage(ImageIO.read(ResourceUtils.getFile("classpath:1234.png")), 0, 0, width, 60, null);
    //g3.drawImage(ImageIO.read(ResourceUtils.getFile("classpath:1234.png")), 0, 265, width, 30, null);

    g3.drawImage(bi, 3, 65, bi.getWidth(), bi.getHeight(), null);
    g3.dispose();

    ImageIO.write(bi3, "JPEG", new FileOutputStream(fileName));// 保存图片 JPEG表示保存格式
  }

  @Test
  public void Test_GetPrinter() {
    HashPrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
    DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(flavor, attributeSet);
    for (PrintService printService : printServices) {
      System.out.println(printService.getName());
    }
  }

  @Test
  public void Test() throws IOException, WriterException {
    Device device = new Device();
    device.setDeviceModel("12345");
    device.setDeviceId("https://www.baidu.com");
    device.setDeviceNo("34567");
    device.setDeviceName("456789");
    device.setInstallPlace("abcde");
    device.setPurchaseTime("2022-08-01 12:10:10");
    device.setManufacturer("dcdef");
    device.setDepartId("opqxyz");
    String fileName = "D:\\images\\1234.png";
    createQrCode2(fileName, device);

    drawImage(fileName, 1);
  }
}
