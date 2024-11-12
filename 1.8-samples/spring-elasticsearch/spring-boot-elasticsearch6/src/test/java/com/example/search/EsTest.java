package com.example.search;

import com.alibaba.fastjson.JSON;
import com.example.search.mapping.ProductDocument;
import com.example.search.mapping.ProductDocumentBuilder;
import com.example.search.model.Page;
import com.example.search.service.EsSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {

  private Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private EsSearchService esSearchService;

  @Test
  public void save() {
    log.info("【创建索引前的数据条数】：{}", esSearchService.getAll().size());

    ProductDocument productDocument = ProductDocumentBuilder.create()
        .addId(System.currentTimeMillis() + "01")
        .addProductName("无印良品 MUJI 基础润肤化妆水")
        .addProductDesc("无印良品 MUJI 基础润肤化妆水 高保湿型 200ml")
        .addCreateTime(new Date()).addUpdateTime(new Date())
        .builder();

    ProductDocument productDocument1 = ProductDocumentBuilder.create()
        .addId(System.currentTimeMillis() + "02")
        .addProductName("荣耀 V10 尊享版")
        .addProductDesc("荣耀 V10 尊享版 6GB+128GB 幻夜黑 移动联通电信4G全面屏游戏手机 双卡双待")
        .addCreateTime(new Date()).addUpdateTime(new Date())
        .builder();

    ProductDocument productDocument2 = ProductDocumentBuilder.create()
        .addId(System.currentTimeMillis() + "03")
        .addProductName("资生堂(SHISEIDO) 尿素红罐护手霜")
        .addProductDesc("日本进口 资生堂(SHISEIDO) 尿素红罐护手霜 100g/罐 男女通用 深层滋养 改善粗糙")
        .addCreateTime(new Date()).addUpdateTime(new Date())
        .builder();

    esSearchService.save(productDocument, productDocument1, productDocument2);

    log.info("【创建索引ID】:{},{},{}", productDocument.getId(), productDocument1.getId(), productDocument2.getId());
    log.info("【创建索引后的数据条数】：{}", esSearchService.getAll().size());
  }

  @Test
  public void getAll() {
    esSearchService.getAll().parallelStream()
        .map(JSON::toJSONString)
        .forEach(System.out::println);
  }

  @Test
  public void deleteAll() {
    esSearchService.deleteAll();
  }

  @Test
  public void getById() {
    log.info("【根据ID查询内容】：{}", JSON.toJSONString(esSearchService.getById("154470178213401")));
  }

  @Test
  public void query() {
    log.info("【根据关键字搜索内容】：{}", JSON.toJSONString(esSearchService.query("无印良品荣耀", ProductDocument.class)));
  }

  @Test
  public void queryHit() {

    String keyword = "联通尿素";
    String indexName = "orders";

    List<Map<String, Object>> searchHits = esSearchService.queryHit(keyword, indexName, "productName", "productDesc");
    log.info("【根据关键字搜索内容，命中部分高亮，返回内容】：{}", JSON.toJSONString(searchHits));
  }

  @Test
  public void queryHitByPage() {
    String keyword = "联通尿素";
    String indexName = "orders";

    Page<Map<String, Object>> searchHits = esSearchService.queryHitByPage(1, 1, keyword, indexName, "productName", "productDesc");
    log.info("【分页查询，根据关键字搜索内容，命中部分高亮，返回内容】：{}", JSON.toJSONString(searchHits));
  }

  @Test
  public void deleteIndex() {
    log.info("【删除索引库】");
    esSearchService.deleteIndex("orders");
  }

}