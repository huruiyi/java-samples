package vip.fairy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.dom4j.io.XMLWriter;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;


public class XmlTest {

  public static void main(String[] args) throws IOException {
    // Create a new document
    Document document = DocumentHelper.createDocument();

    // Add root element
    Element rootElement = document.addElement("root");

    // Add child elements
    rootElement.addElement("child").setText("Child 1-测试1");
    rootElement.addElement("child").setText("Child 2-测试2");

    // Create an output format that suppresses the XML declaration
    OutputFormat format = OutputFormat.createPrettyPrint();
   format.setEncoding("gb2312"); // 设置XML文档的编码类型
    // 设置元素是否有子节点都输出
    format.setExpandEmptyElements(true);
    format.setSuppressDeclaration(false);
    format.setIndent(true); // 设置是否缩进
    format.setNewlines(true); // 设置是否换行

    // Write the document to a file
    OutputStream out = new FileOutputStream("output.xml");

    XMLWriter writer = new XMLWriter(out, format);
    writer.write(document);
    writer.close();
    out.close();
  }
}
