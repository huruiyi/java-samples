package com.example.unfiled;

import com.example.xml.ch.xpath.Dom4jTest1;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

public class Github {

  private static final Logger log = LoggerFactory.getLogger(Dom4jTest1.class);


  public static void main(String[] args) throws IOException {

    for (int i = 1; i <= 34; i++) {
      String url = MessageFormat.format("https://github.com/hendisantika?language=&page={0}&q=&sort=&tab=repositories&type=source", i);
      Document doc = Jsoup.connect(url).get();
      log.info(doc.title());
      Elements elements = doc.select(".wb-break-all");
      elements.forEach(element -> {
        List<Node> nodes = element.childNodes();
        Node node = nodes.get(1);
        System.out.println("https://github.com" + node.attr("href"));
      });
    }


  }
}
