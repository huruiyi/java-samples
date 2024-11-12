package com.example.xml.appa.ch02.DumpUserInfo;

import static java.lang.System.err;

import com.example.xml.ch.xpath.Test;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class DumpUserInfo {

  public static void main(String[] args) {
    try {
      SAXParserFactory spf = SAXParserFactory.newInstance();
      spf.setNamespaceAware(true);
      SAXParser sp = spf.newSAXParser();
      XMLReader xmlReader = sp.getXMLReader();
      Handler handler = new Handler();
      xmlReader.setContentHandler(handler);
      String path = Test.class.getClassLoader().getResource("xml/tomcat-users.xml").getPath();//注意getResource("")里面是空字符串
      FileReader fr = new FileReader(path);
      xmlReader.parse(new InputSource(fr));
    } catch (IOException ioe) {
      err.printf("IOE: %s%n", ioe);
    } catch (ParserConfigurationException pce) {
      err.printf("PCE: %s%n", pce);
    } catch (SAXException saxe) {
      err.printf("SAXE: %s%n", saxe);
    }
  }
}

