package vip.fairy.xml.ch.ch05.DOMSearch;

import static java.lang.System.err;
import static java.lang.System.out;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMSearch {

  public static void main(String[] args) {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse("contacts.xml");
      List<String> contactNames = new ArrayList<String>();
      NodeList contacts = doc.getElementsByTagName("contact");
      for (int i = 0; i < contacts.getLength(); i++) {
        Element contact = (Element) contacts.item(i);
        NodeList cities = contact.getElementsByTagName("city");
        boolean chicago = false;
        for (int j = 0; j < cities.getLength(); j++) {
          Element city = (Element) cities.item(j);
          NodeList children = city.getChildNodes();
          StringBuilder sb = new StringBuilder();
          for (int k = 0; k < children.getLength(); k++) {
            Node child = children.item(k);
            if (child.getNodeType() == Node.TEXT_NODE) {
              sb.append(child.getNodeValue());
            }
          }
          if (sb.toString().equals("Chicago")) {
            chicago = true;
            break;
          }
        }
        if (chicago) {
          NodeList names = contact.getElementsByTagName("name");
          contactNames.add(names.item(0).getFirstChild().getNodeValue());
        }
      }
      for (String contactName : contactNames) {
        out.println(contactName);
      }
    } catch (IOException ioe) {
      err.printf("IOE: %s%n", ioe.toString());
    } catch (SAXException saxe) {
      err.printf("SAXE: %s%n", saxe.toString());
    } catch (FactoryConfigurationError fce) {
      err.printf("FCE: %s%n", fce.toString());
    } catch (ParserConfigurationException pce) {
      err.printf("PCE: %s%n", pce.toString());
    }
  }
}
