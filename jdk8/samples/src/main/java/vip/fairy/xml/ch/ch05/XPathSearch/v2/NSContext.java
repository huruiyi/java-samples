package vip.fairy.xml.ch.ch05.XPathSearch.v2;

import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;

public class NSContext implements NamespaceContext {

  @Override
  public String getNamespaceURI(String prefix) {
    if (prefix == null) {
      throw new
          IllegalArgumentException("prefix is null");
    } else if (prefix.equals("tt")) {
      return "http://www.javajeff.ca/";
    } else {
      return null;
    }
  }

  @Override
  public String getPrefix(String uri) {
    return null;
  }

  @Override
  public Iterator<String> getPrefixes(String uri) {
    return null;
  }
}
