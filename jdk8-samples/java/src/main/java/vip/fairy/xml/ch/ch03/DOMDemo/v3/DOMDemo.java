package vip.fairy.xml.ch.ch03.DOMDemo.v3;

import static java.lang.System.err;
import static java.lang.System.out;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSParser;

public class DOMDemo {

  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      err.println("usage: java DOMDemo xmlfile");
      return;
    }
    DOMImplementationLS ls = (DOMImplementationLS)
        DOMImplementationRegistry.newInstance().
            getDOMImplementation("LS");
    if (ls == null) {
      err.println("load and save not supported");
      return;
    }
    LSParser parser =
        ls.createLSParser(DOMImplementationLS.
            MODE_SYNCHRONOUS, null);
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
    out.printf("Element: %s, %s, %s, %s%n",
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
