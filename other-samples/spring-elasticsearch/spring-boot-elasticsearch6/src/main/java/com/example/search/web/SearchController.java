package com.example.search.web;

import com.example.search.mapping.ProductDocument;
import com.example.search.mapping.ProductDocumentBuilder;
import com.example.search.model.Page;
import com.example.search.service.EsSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * elasticsearch 搜索
 */
@RestController
public class SearchController {

  private Logger log = LoggerFactory.getLogger(getClass());

  @Resource
  private EsSearchService esSearchService;

  @RequestMapping(value = "/init")
  public String init() {
    log.info("历史数据清理,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
    esSearchService.deleteAll();
    log.info("数据初始化开始.");
    for (int i = 1; i <= 60; i++) {
      ProductDocument productDocument = ProductDocumentBuilder.create()
          .addId(String.format("%03d", i))
          .addProductName("荣耀 V10 尊享版")
          .addProductDesc("荣耀 V10 尊享版 6GB+128GB 幻夜黑 移动联通电信4G全面屏游戏手机 双卡双待")
          .addCreateTime(new Date())
          .addUpdateTime(new Date())
          .builder();
      esSearchService.save(productDocument);
    }
    for (int i = 61; i <= 120; i++) {
      ProductDocument productDocument = ProductDocumentBuilder.create()
          .addId(String.format("%03d", i))
          .addProductName("资生堂(SHISEIDO) 尿素红罐护手霜")
          .addProductDesc("日本进口 资生堂(SHISEIDO) 尿素红罐护手霜 100g/罐 男女通用 深层滋养 改善粗糙")
          .addCreateTime(new Date())
          .addUpdateTime(new Date())
          .builder();
      esSearchService.save(productDocument);
    }

    for (int i = 121; i <= 180; i++) {
      ProductDocument productDocument = ProductDocumentBuilder.create()
          .addId(String.format("%03d", i))
          .addProductName("无印良品 MUJI 基础润肤化妆水")
          .addProductDesc("无印良品 MUJI 基础润肤化妆水 高保湿型 200ml")
          .addCreateTime(new Date())
          .addUpdateTime(new Date())
          .builder();
      esSearchService.save(productDocument);
    }
    log.info("数据初始化结束,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");

    return "Hello Elasticsearch.........";
  }

  /**
   * 新增 / 修改索引
   *
   * @return
   */
  @RequestMapping("save")
  public String add(@RequestBody ProductDocument productDocument) {
    esSearchService.save(productDocument);
    return "success";
  }

  /**
   * 删除索引
   *
   * @return
   */
  @RequestMapping("delete/{id}")
  public String delete(@PathVariable String id) {
    esSearchService.delete(id);
    return "success";
  }

  /**
   * 清空索引
   *
   * @return
   */
  @RequestMapping("delete_all")
  public String deleteAll(@PathVariable String id) {
    esSearchService.deleteAll();
    return "success";
  }

  /**
   * 根据ID获取
   *
   * @return
   */
  @RequestMapping("get/{id}")
  public ProductDocument getById(@PathVariable String id) {
    return esSearchService.getById(id);
  }

  /**
   * 根据获取全部
   *
   * @return
   */
  @RequestMapping("get_all")
  public List<ProductDocument> getAll() {
    return esSearchService.getAll();
  }

  /**
   * 搜索
   *
   * @param keyword
   * @return
   */
  @RequestMapping("query/{keyword}")
  public List<ProductDocument> query(@PathVariable String keyword) {
    return esSearchService.query(keyword, ProductDocument.class);
  }

  /**
   * 搜索，命中关键字高亮 http://localhost:9000/query_hit?keyword=无印良品荣耀&indexName=orders&fields=productName,productDesc
   *
   * @param keyword   关键字
   * @param indexName 索引库名称
   * @param fields    搜索字段名称，多个以“，”分割
   * @return
   */
  @RequestMapping("query_hit")
  public List<Map<String, Object>> queryHit(@RequestParam String keyword, @RequestParam String indexName, @RequestParam String fields) {
    String[] fieldNames = {};
    if (fields.contains(",")) {
      fieldNames = fields.split(",");
    } else {
      fieldNames[0] = fields;
    }
    return esSearchService.queryHit(keyword, indexName, fieldNames);
  }

  /**
   * 搜索，命中关键字高亮 http://localhost:9000/query_hit_page?keyword=无印良品荣耀&indexName=orders&fields=productName,productDesc&pageNo=1&pageSize=1
   *
   * @param pageNo    当前页
   * @param pageSize  每页显示的数据条数
   * @param keyword   关键字
   * @param indexName 索引库名称
   * @param fields    搜索字段名称，多个以“，”分割
   * @return
   */
  @RequestMapping("query_hit_page")
  public Page<Map<String, Object>> queryHitByPage(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String keyword,
      @RequestParam String indexName, @RequestParam String fields) {
    String[] fieldNames = {};
    if (fields.contains(",")) {
      fieldNames = fields.split(",");
    } else {
      fieldNames[0] = fields;
    }
    return esSearchService.queryHitByPage(pageNo, pageSize, keyword, indexName, fieldNames);
  }

  /**
   * 删除索引库
   *
   * @param indexName
   * @return
   */
  @RequestMapping("delete_index/{indexName}")
  public String deleteIndex(@PathVariable String indexName) {
    esSearchService.deleteIndex(indexName);
    return "success";
  }
}
