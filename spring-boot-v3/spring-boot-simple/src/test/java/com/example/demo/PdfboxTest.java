package com.example.demo;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class PdfboxTest {

  public static void main(String[] args) throws IOException {
    PDDocument document = new PDDocument();
    PDPage page = new PDPage();
    document.addPage(page);
    InputStream fontIS = new ClassPathResource("fonts/微软雅黑Bbold.ttf").getInputStream();
    PDFont font = PDType0Font.load(document, fontIS, false);

    PDPageContentStream contentStream = new PDPageContentStream(document, page);
    contentStream.setFont(font, 12);
    contentStream.beginText();
    contentStream.newLineAtOffset(100, 700);
    contentStream.showText("Hello World,世界你好！！");
    contentStream.endText();

    contentStream.close();

    File file = new File("20131204.pdf");
    document.save(file);
  }
}
