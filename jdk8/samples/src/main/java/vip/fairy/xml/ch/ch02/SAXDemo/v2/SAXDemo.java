package vip.fairy.xml.ch.ch02.SAXDemo.v2;

import static java.lang.System.err;

import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SAXDemo {

  final static String FEAT_NSP =
      "http://xml.org/sax/features/namespace-prefixes";

  final static String FEAT_VAL =
      "http://xml.org/sax/features/validation";

  final static String PROP_LH =
      "http://xml.org/sax/properties/lexical-handler";

  final static String PROP_SL =
      "http://java.sun.com/xml/jaxp/properties/" +
          "schemaLanguage";

  final static String PROPVAL_XMLS =
      "http://www.w3.org/2001/XMLSchema";

  public static void main(String[] args) {
    if (args.length < 1 || args.length > 2) {
      err.println("usage: java SAXDemo xmlfile [v]");
      return;
    }
    try {
      SAXParserFactory spf =
          SAXParserFactory.newInstance();
      spf.setNamespaceAware(true);
      SAXParser sp = spf.newSAXParser();
      XMLReader xmlr = sp.getXMLReader();
      if (args.length == 2 && args[1].equals("v")) {
        xmlr.setFeature(FEAT_VAL, true);
      }
      xmlr.setFeature(FEAT_NSP, true);
      Handler handler = new Handler();
      xmlr.setContentHandler(handler);
      xmlr.setDTDHandler(handler);
      xmlr.setEntityResolver(handler);
      xmlr.setErrorHandler(handler);
      xmlr.setProperty(PROP_LH, handler);
      xmlr.setProperty(PROP_SL, PROPVAL_XMLS);
      FileReader fr = new FileReader(args[0]);
      xmlr.parse(new InputSource(fr));
    } catch (IOException ioe) {
      err.printf("IOE: %s%n", ioe.toString());
    } catch (ParserConfigurationException pce) {
      err.printf("PCE: %s%n", pce.toString());
    } catch (SAXException saxe) {
      err.printf("SAXE: %s%n", saxe.toString());
    }
  }
}
