package vip.fairy.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class SXSSFExcelTest {

  public static void main(String[] args) {

    // 同样的一份文件写入，XSSFWorkbook需要1200+M，SXSSFWorkbook只需要148M。所以大文件的写入，使用SXSSFWorkbook是可以更加节省内存的。
    // 创建一个新的工作簿
    SXSSFWorkbook workbook = new SXSSFWorkbook();

    // 创建一个新的表格
    Sheet sheet = workbook.createSheet("Example Sheet");
    for (int i = 0; i < 10000; i++) {
      // 创建行（从0开始计数）
      Row row = sheet.createRow(i);
      for (int j = 0; j < 100; j++) {
        // 在行中创建单元格（从0开始计数）
        Cell cell = row.createCell(j);

        // 设置单元格的值
        cell.setCellValue(UUID.randomUUID().toString());
      }
    }

    // 设置文件路径和名称
    String filename = "example.xlsx";

    try (FileOutputStream outputStream = new FileOutputStream(filename)) {
      // 将工作簿写入文件
      workbook.write(outputStream);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        // 关闭工作簿资源
        workbook.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
