package vip.fairy.xml.appa.ch03.DOMDemo;

import static java.lang.System.err;
import static java.lang.System.out;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSParser;

class ErrHandler implements DOMErrorHandler {

  @Override
  public boolean handleError(DOMError error) {
    short severity = error.getSeverity();
    if (severity == error.SEVERITY_ERROR) {
      System.out.printf("DOM3 error: %s%n", error.getMessage());
    } else if (severity == error.SEVERITY_FATAL_ERROR) {
      System.out.printf("DOM3 fatal error: %s%n", error.getMessage());
    } else if (severity == error.SEVERITY_WARNING) {
      System.out.printf("DOM3 warning: %s%n", error.getMessage());
    }
    return true;
  }
}

public class DOMDemo {

  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      err.println("usage: java DOMDemo xmlfile");
      return;
    }
    DOMImplementationLS ls = (DOMImplementationLS) DOMImplementationRegistry.newInstance().getDOMImplementation("LS");
    LSParser parser = ls.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
    DOMConfiguration config = parser.getDomConfig();
    config.setParameter("validate", Boolean.TRUE);
    config.setParameter("error-handler", new ErrHandler());
    Document doc = parser.parseURI(args[0]);
    if (doc.hasChildNodes()) {
      NodeList nl = doc.getChildNodes();
      for (int i = 0; i < nl.getLength(); i++) {
        Node node = nl.item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          dump((Element) node);
        }
      }
    }
  }

  static void dump(Element e) {
    System.out.printf("Element: %s, %s, %s, %s%n",
        e.getNodeName(), e.getLocalName(),
        e.getPrefix(), e.getNamespaceURI());
    NamedNodeMap nnm = e.getAttributes();
    if (nnm != null) {
      for (int i = 0; i < nnm.getLength(); i++) {
        Node node = nnm.item(i);
        Attr attr =
            e.getAttributeNode(node.getNodeName());
        out.printf("  Attribute %s = %s%n",
            attr.getName(), attr.getValue());
      }
    }
    NodeList nl = e.getChildNodes();
    for (int i = 0; i < nl.getLength(); i++) {
      Node node = nl.item(i);
      if (node instanceof Element) {
        dump((Element) node);
      }
    }
  }
}
