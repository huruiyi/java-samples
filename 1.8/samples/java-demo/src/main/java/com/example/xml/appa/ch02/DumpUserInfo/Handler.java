package com.example.xml.appa.ch02.DumpUserInfo;

import static java.lang.System.out;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    if (localName.equals("user")) {
      for (int i = 0; i < attributes.getLength(); i++) {
        out.printf("%s = %s%n", attributes.getLocalName(i), attributes.getValue(i));
      }
      out.println();
    }
  }

}
