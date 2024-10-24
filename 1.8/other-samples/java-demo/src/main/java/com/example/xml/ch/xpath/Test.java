package com.example.xml.ch.xpath;

import java.util.List;
import java.util.Objects;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

  private static final Logger log = LoggerFactory.getLogger(Test.class);

  public static void main(String[] args) throws DocumentException {
    String dir = System.getProperty("user.dir");
    log.info(dir);

    String path = Objects.requireNonNull(Test.class.getClassLoader().getResource("xml/web.xml")).getPath();
    log.info(path);
    Document document = new SAXReader().read(path);

    List<Node> nodes = document.selectNodes("/web-app/servlet/servlet-name");
    for (Node node : nodes) {
      log.info("name:{},text:{}", node.getName(), node.getText());
    }

    Element ele = (Element) document.selectSingleNode("//servlet/servlet-name");
    log.info(ele.getText());

    log.info("*****************************************************");
    List<Element> list = document.getRootElement().elements();
    for (Element element : list) {
      log.info("servlet-name:{}", element.elementText("servlet-name"));
      log.info("url-pattern:{}", element.elementText("url-pattern"));
      log.info("servlet-class:{}", element.elementText("servlet-class"));
    }

    String value = document.getRootElement().attributeValue("version");
    log.info(value);

    Element servletClass = (Element) document.selectSingleNode("//servlet-class");
    Element urlPattern = (Element) document.selectSingleNode("//url-pattern");

    String classText = servletClass.getText();
    String urlText = urlPattern.getText();
    log.info("urlText:{},classText:{}", urlText, classText);
  }

}
