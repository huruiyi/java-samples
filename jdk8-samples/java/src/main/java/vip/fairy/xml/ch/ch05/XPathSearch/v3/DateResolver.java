package vip.fairy.xml.ch.ch05.XPathSearch.v3;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathFunction;
import javax.xml.xpath.XPathFunctionResolver;

public class DateResolver implements XPathFunctionResolver {

  private static final QName name =
      new QName("http://www.javajeff.ca/", "date", "tt");

  @Override
  public XPathFunction resolveFunction(QName name,
      int arity) {
    if (name.equals(this.name) && arity == 1) {
      return new Date();
    }
    return null;
  }
}
