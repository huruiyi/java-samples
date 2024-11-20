package vip.fairy.unfiled;

import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Test_XML {

  @Test
  public void Test1() throws ParserConfigurationException, TransformerException {
    // 创建解析器工厂
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = factory.newDocumentBuilder();
    Document document = db.newDocument();
    document.setXmlStandalone(true);
    Element bookstore = document.createElement("claimReportInfo");

    Element book = document.createElement("policyNo");
    book.setTextContent("AHYXAGD24118E0004516");
    bookstore.appendChild(book);

    document.appendChild(bookstore);

    TransformerFactory tff = TransformerFactory.newInstance();
    Transformer tf = tff.newTransformer();

    StringWriter writer = new StringWriter();

    tf.transform(new DOMSource(document), new StreamResult(writer));
    System.out.println(writer);
    //tf.transform(new DOMSource(document), new StreamResult(new File("book1---huhu.xml")));
    //System.out.println("生成book1.xml成功");
  }

  @Test
  public void Test2() throws DocumentException {
    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
        "<claimReportInfoResponse>\n" +
        "  <registerNo>DHYXHYE24119000005</registerNo>" +
        "  <comments>报案成功</comments>" +
        "</claimReportInfoResponse>";

    org.dom4j.Document doc = DocumentHelper.parseText(xml);
    String registerNo = doc.getRootElement().elementText("registerNo");
    System.out.println(registerNo);
  }
}
