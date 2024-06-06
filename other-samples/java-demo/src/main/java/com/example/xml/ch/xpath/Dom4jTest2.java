package com.example.xml.ch.xpath;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dom4jTest2 {

  private static final Logger log = LoggerFactory.getLogger(Dom4jTest2.class);

  static void readXml() throws DocumentException {
    String path = Test.class.getClassLoader().getResource("xml/students.xml").getPath();
    log.info("path:{}", path);

    SAXReader reader = new SAXReader();
    Document document = reader.read(path);
    // 获取 XML 文档的根节点，即 <students> 标签
    Element root = document.getRootElement();
    // elements 方法用于获取指定的标签集合
    List<Element> students = root.elements("student");

    for (Element student : students) {
      // attribute 方法用于获取标签属性
      Attribute noAttr = student.attribute("no");
      String no = noAttr.getText();
      log.info("no: {}", no);

      // element 方法用于获取唯一的子节点对象
      Element nameElement = student.element("name");
      // getText 方法用于获取标签文本
      String name = nameElement.getText();
      log.info("name: {}", name);

      // elementText 方法用于获取指定标签的文本
      String age = student.elementText("age");
      log.info("age: {}", age);

      String score = student.elementText("score");
      log.info("score: {}", score);

      Element parents = student.element("parents");
      String father = parents.elementText("father");
      String mother = parents.elementText("mother");

      log.info("father: {}", father);
      log.info("mother: {}", mother);

      log.info("---------");
    }
  }

  static void writeXml() throws DocumentException, IOException {
    String path = Test.class.getClassLoader().getResource("xml/students_change.xml").getPath();
    log.info("path:{}", path);

    SAXReader reader = new SAXReader();
    Document document = reader.read(path);
    // 获取 XML 文档的根节点，即 <students> 标签
    Element root = document.getRootElement();

    Element student = root.addElement("student");
    student.addAttribute("no", "003");
    student.addElement("name").setText("王五");
    student.addElement("age").setText("20");
    student.addElement("score").setText("100");
    Element parents = student.addElement("parents");
    parents.addElement("father").setText("阿发2");
    parents.addElement("mother").setText("阿梅2");

    FileOutputStream fos = new FileOutputStream(path);
    Writer writer = new OutputStreamWriter(fos);
    document.write(writer);
    writer.close();
    fos.close();
  }

  public static void main(String[] args) throws DocumentException, IOException {
    readXml();
    writeXml();
  }


}
