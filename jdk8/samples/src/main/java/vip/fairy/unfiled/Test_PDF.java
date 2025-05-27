package vip.fairy.unfiled;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class Test_PDF {

  public static String getPdfFileText(String fileName) throws IOException {
    PdfReader reader = new PdfReader(fileName);
    PdfReaderContentParser parser = new PdfReaderContentParser(reader);
    StringBuffer buff = new StringBuffer();
    TextExtractionStrategy strategy;
    for (int i = 1; i <= reader.getNumberOfPages(); i++) {
      strategy = parser.processContent(i,
          new SimpleTextExtractionStrategy());
      buff.append(strategy.getResultantText());
    }
    return buff.toString();
  }


  @Test
  public void Test0() throws IOException {

    System.out.println("********************************");
    System.out.println(getPdfFileText("Pdf-Model.pdf"));
    System.out.println("********************************");

    //获取 PdfReader 对象,文件名称要是在classpath中的文件
    String src = "Pdf-Model.pdf";
    PdfReader reader = new PdfReader(src);

    System.out.println(reader.getFileLength());

    //获取pdf中页数
    int pageCount = reader.getNumberOfPages();
    System.out.println("page count : " + pageCount);

    int totalSize = 0;
    int flag = 0;
    for (int i = 1; i <= pageCount; i++) {

      int length = reader.getPageContent(i).length;
      ArrayList<Chunk> chunks = (ArrayList<Chunk>) reader.getPageSize(i).getChunks();

      System.out.println(length);
      totalSize += length;
    }

    System.out.println(totalSize);
    System.out.println("diff:----" + (reader.getFileLength() - totalSize));
  }

  @Test
  public void Test2() throws IOException, DocumentException {
    PdfReader reader = new PdfReader("Personal-Info.pdf");

    FileOutputStream fileOutputStream = new FileOutputStream("PersonalInfo-DEST.pdf");
    PdfStamper pdfStamper = new PdfStamper(reader, fileOutputStream); // 生成的输出流
    AcroFields fields = pdfStamper.getAcroFields();

    Set<Map.Entry<String, AcroFields.Item>> entrySet = fields.getFields().entrySet();

    for (Map.Entry<String, AcroFields.Item> entry : entrySet) {
      System.out.println(entry.getKey() + " " + entry.getValue());
    }

    fields.setField("nation", "中国");
    fields.setField("name", "胡睿毅");

    pdfStamper.close();
    fileOutputStream.close();
  }

  public void Test3() throws IOException, DocumentException {
    File outputFile = new File("C:\\Users\\vic\\Downloads\\Test\\output2.pdf");
    PdfReader reader = new PdfReader("C:\\Users\\vic\\Downloads\\Test\\1.pdf");

    int numberOfPages = reader.getNumberOfPages();

    int total = 0;
    Document document = new Document(reader.getPageSizeWithRotation(1));
    PdfCopy writer = null;
    PdfImportedPage page = null;
    int limit = 400 * 1024;
    for (int i = 1; i <= numberOfPages; i++) {
      writer = new PdfCopy(document, new FileOutputStream(outputFile));
      document.open();
      page = writer.getImportedPage(reader, i);
      writer.addPage(page);
      int currentDocumentSize = (int) writer.getCurrentDocumentSize();
      if (currentDocumentSize > limit) {
        System.out.println(currentDocumentSize + "，超出限制。。");
      } else {
        System.out.println(currentDocumentSize);
      }
      total += currentDocumentSize;

      document.close();
    }
    if (writer != null) {
      writer.close();
    }
    outputFile.delete();
    System.out.println("总页数：" + numberOfPages);
    System.out.println("实际大小：" + reader.getFileLength());
    System.out.println("拆分大小：" + total);
  }
}
