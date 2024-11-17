package com.example.unfiled;

import java.util.List;
import java.util.function.Consumer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;

public class ProvinceCityCounty {

  static RetryTemplate template = RetryTemplate.builder().maxAttempts(10).fixedBackoff(3000).retryOn(Exception.class).build();

  public static void main(String[] args) throws Exception {
    Document doc = Jsoup.connect("http://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2023/index.html").get();
    Elements elements = doc.getElementsByTag("a");
    for (Element element : elements) {
      String href = element.attr("href");
      if (!href.contains("http://www.miibeian.gov.cn/")) {
        TextNode node = (TextNode) element.childNode(0);
        String provinceUrl = "http://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2023/" + href;
        String type = "0,";
        String parentId = str("000000000000");
        String id = strId(getId(provinceUrl) + "0000000000");
        String name = strName(node.text());
        String townType = "000";
        System.out.println(type + townType + "," + parentId + id + name);
        try {
          printCityInfo(provinceUrl);
        } catch (Exception e) {
          retryPrintInfo(provinceUrl, ProvinceCityCounty::printCityInfo);
        }
      }
    }
  }

  private static void printCityInfo(String url) {
    Document document = getDocument(url);
    Elements elements = document.getElementsByClass("citytr");
    elements.forEach(element -> {
      Elements cityCodeName = element.getElementsByTag("a");
      Element element1 = cityCodeName.get(1);

      String type = "1,";
      String parentId = str(getId(url) + "0000000000");
      String id = strId(cityCodeName.get(0).text());
      String name = strName(element1.text());
      String townType = "000";
      System.out.println(type + townType + "," + parentId + id + name);

      String urlNext = "http://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2023/" + element1.attr("href");
      try {
        printCountyInfo(urlNext);
      } catch (Exception e) {
        retryPrintInfo(urlNext, ProvinceCityCounty::printCountyInfo);
      }
    });
  }

  private static void printCountyInfo(String url) {
    Document document = getDocument(url);
    Elements elements = document.getElementsByClass("countytr");

    elements.forEach(element -> {
      Elements countyCodeName = element.getElementsByTag("a");
      if (!countyCodeName.isEmpty()) {
        Element element1 = countyCodeName.get(1);

        String type = "2,";
        String parentId = str(getId(url) + "00000000");
        String id = strId(countyCodeName.get(0).text());
        String name = strName(element1.text());
        String townType = "000";
        System.out.println(type + townType + "," + parentId + id + name);

        String urlNext = url.substring(0, url.lastIndexOf("/")) + "/" + element1.attr("href");
        try {
          printTownInfo(urlNext);
        } catch (Exception e) {
          retryPrintInfo(urlNext, ProvinceCityCounty::printTownInfo);
        }
      }
    });
  }

  private static void printTownInfo(String url) {
    Document document = getDocument(url);
    Elements elements = document.getElementsByClass("towntr");

    elements.forEach(element -> {
      Elements townCodeName = element.getElementsByTag("a");
      if (!townCodeName.isEmpty()) {
        Element element1 = townCodeName.get(1);

        String type = "3,";
        String parentId = str(getId(url) + "000000");
        String id = strId(townCodeName.get(0).text());
        String name = strName(element1.text());
        String townType = "000";
        System.out.println(type + townType + "," + parentId + id + name);

        String urlNext = url.substring(0, url.lastIndexOf("/")) + "/" + element1.attr("href");
        try {
          printVillageInfo(urlNext);
        } catch (Exception exception) {
          retryPrintInfo(urlNext, ProvinceCityCounty::printVillageInfo);
        }
      }
    });

  }

  private static void retryPrintInfo(String urlNext, Consumer<String> printInfo) {
    try {
      FailingRetryCallback retryCallback = new FailingRetryCallback();
      retryCallback.setUrl(urlNext);
      retryCallback.setPrintInfo(printInfo);
      template.execute(retryCallback);
    } catch (Exception exception) {

    }
  }

  private static void printVillageInfo(String url) {
    Document document = getDocument(url);
    Elements elements = document.getElementsByClass("villagetr");
    elements.forEach(element -> {
      List<Node> nodes = element.childNodes();
      if (!nodes.isEmpty()) {
        String code = ((TextNode) nodes.get(0).childNode(0)).text();
        String category = ((TextNode) nodes.get(1).childNode(0)).text();
        String nameStr = ((TextNode) nodes.get(2).childNode(0)).text();

        String type = "4,";
        String parentId = str(getId(url) + "000");
        String id = strId(code);
        String name = strName(nameStr);
        String townType = category;
        System.out.println(type + townType + "," + parentId + id + name);
      }
    });

  }

  private static Document getDocument(String url) {
    try {
      return Jsoup.connect(url).get();
    } catch (Exception exception) {
      return null;
    }
  }

  static String str(String str) {
    return String.format("%12s,", str);
  }

  static String strId(String str) {
    return String.format("%12s,", str);
  }

  static String strName(String str) {
    return String.format("%s", str);
  }

  static String getId(String href) {
    String url = href.substring(href.lastIndexOf("/") + 1);
    return url.split("\\.")[0];
  }

  static class FailingRetryCallback implements RetryCallback<Object, Exception> {

    private String url;
    private Consumer<String> printInfo;

    public void setPrintInfo(Consumer<String> printInfo) {
      this.printInfo = printInfo;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public Object doWithRetry(RetryContext context) {
      printInfo.accept(url);
      return null;
    }

  }
}
