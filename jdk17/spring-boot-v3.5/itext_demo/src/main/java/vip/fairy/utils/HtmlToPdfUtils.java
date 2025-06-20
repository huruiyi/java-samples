package vip.fairy.utils;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.font.FontProvider;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class HtmlToPdfUtils {

  public static void convertToPdf(String html, OutputStream outputStream) throws IOException {
    PdfWriter pdfWriter = new PdfWriter(outputStream);
    PdfDocument pdfDocument = new PdfDocument(pdfWriter);
    // 设置为A4大小
    pdfDocument.setDefaultPageSize(PageSize.A4);

    // 添加中文字体支持
    ConverterProperties properties = new ConverterProperties();
    FontProvider fontProvider = new FontProvider();

    // 添加自定义字体，例如宋体
    String fontPath = Objects.requireNonNull(HtmlToPdfUtils.class.getResource("/fonts/STSong.ttf")).getPath();
    PdfFont stSong = PdfFontFactory.createFont(fontPath, PdfEncodings.WINANSI);
    fontProvider.addFont(stSong.getFontProgram(), PdfEncodings.IDENTITY_H);

    properties.setFontProvider(fontProvider);
    if (html != null) {
      // 生成pdf文档
      HtmlConverter.convertToPdf(html, pdfDocument, properties);
      pdfWriter.close();
      pdfDocument.close();
    } else {
      throw new RuntimeException("HTML 内容不能为空");
    }
  }

}
