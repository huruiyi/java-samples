package vip.fairy.unfiled;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vip.fairy.xml.ch.xpath.Dom4jTest1;

public class Github {

  private static final Logger log = LoggerFactory.getLogger(Dom4jTest1.class);


  public static void main(String[] args) throws IOException {
    String baseUrl = "https://github.com/orgs/PacktPublishing/repos_list?q=&page=";
    int page = 1;

    try (CloseableHttpClient client = HttpClients.createDefault()) {
      while (true) {
        HttpGet request = new HttpGet(baseUrl + page);
        request.setHeader("User-Agent",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
        request.setHeader("Cookie",
            "_octo=GH1.1.1942316512.1734727799; _device_id=f179227f6d235b7d331ca18883e1ab5a; user_session=iJ2KBkRcQTE5wE8nv_rCJ55jVYEKlq4EbmW5v4RTPOkS5DRs; __Host-user_session_same_site=iJ2KBkRcQTE5wE8nv_rCJ55jVYEKlq4EbmW5v4RTPOkS5DRs; logged_in=yes; dotcom_user=huruiyi; cpu_bucket=xlg; preferred_color_mode=light; tz=Asia%2FShanghai; color_mode=%7B%22color_mode%22%3A%22light%22%2C%22light_theme%22%3A%7B%22name%22%3A%22light_tritanopia%22%2C%22color_mode%22%3A%22light%22%7D%2C%22dark_theme%22%3A%7B%22name%22%3A%22dark_dimmed%22%2C%22color_mode%22%3A%22dark%22%7D%7D; _gh_sess=PV4A2Gcu%2Foygt0z2rOjNq7a%2Fol24rXZpI2C%2BGZGe7pMjTBreTNSTaa0kR1B%2BrEOGrxOJMNbBKXOjvwYcoGtkUgwIr873f2FZVnaUxHqUu8cI8LhuO0tTaU7ltqy0IiyGCVWKMo5koj2qmq80AGTVAzqGHZFaONZxwFPmHJymdNXyN4JDg7%2BWCWp%2FqtCPRrNr1cg2RhB1hq7w9Vo6ZUUCrtOYcZyrUxIEjVIWVHywb0sPfYqQgyYqQJAAxC%2FCqIV%2FELDD6%2FXIsM10rwldSY%2BDswjTRvPqLsC7CUOKdXBNEZd%2FqzIIxBs84Q%3D%3D--rM3Zp%2Fi2irLJsDSH--QCs%2FH3nEETJY4gkVUtSfMA%3D%3D");
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-Type", "application/json");
        String jsonResponse = client.execute(request, response -> {
          if (response.getCode() != 200) {
            System.out.println("请求失败，状态码: " + response.getCode());
            return null;
          }
          return EntityUtils.toString(response.getEntity());
        });

        if (jsonResponse == null) {
          break;
        }
        JSONObject jsonObject = JSON.parseObject(jsonResponse);
        JSONArray repositories = jsonObject.getJSONArray("repositories");

        for (int i = 0; i < repositories.size(); i++) {
          JSONObject repo = repositories.getJSONObject(i);
          String name = repo.getString("name");
          System.out.println(MessageFormat.format("https://github.com/PacktPublishing/{0}.git", name));
        }
        // 检查是否还有下一页
        if (jsonResponse.contains("\"repositories\":[]")) {
          break;
        }
        page++;
        Thread.sleep(2000);
      }
    } catch (Exception e) {
      System.out.println("请求异常: " + e.getMessage());
    }
  }


  void demo1() throws IOException {
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
