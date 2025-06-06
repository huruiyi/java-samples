package vip.fairy.xml.ch.ch04.Copy;

import static java.lang.System.err;

import java.io.FileReader;
import java.io.FileWriter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

public class Copy {

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      err.println("usage: java copy xmlfile1 xmlfile2");
      return;
    }
    XMLInputFactory xmlif = XMLInputFactory.newFactory();
    FileReader fr = new FileReader(args[0]);
    XMLEventReader xmler = xmlif.createXMLEventReader(fr);
    XMLOutputFactory xmlof = XMLOutputFactory.newFactory();
    FileWriter fw = new FileWriter(args[1]);
    XMLEventWriter xmlew;
    xmlew = xmlof.createXMLEventWriter(fw);
    xmlew.add(xmler);
    xmlew.flush();
    xmlew.close();
  }
}
