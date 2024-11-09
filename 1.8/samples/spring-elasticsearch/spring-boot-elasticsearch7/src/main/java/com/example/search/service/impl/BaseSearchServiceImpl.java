package com.example.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.example.search.service.BaseSearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.example.search.model.Page;

@Service
public class BaseSearchServiceImpl<T> implements BaseSearchService<T> {

  @Resource
  private ElasticsearchRestTemplate elasticsearchRestTemplate;

  @Resource
  private RestHighLevelClient client;

  @Override
  public List<T> query(String keyword, Class<T> clazz) {
    SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(new QueryStringQueryBuilder(keyword))
        .withSort(SortBuilders.scoreSort().order(SortOrder.DESC))
        // .withSort(new FieldSortBuilder("createTime").order(SortOrder.DESC))
        .build();

    return elasticsearchRestTemplate.queryForList(searchQuery, clazz);
  }

  /**
   * 高亮显示
   */
  @Override
  public List<Map<String, Object>> queryHit(String keyword, String indexName, String... fieldNames) {
    // 构造查询条件,使用标准分词器.
    QueryBuilder matchQuery = createQueryBuilder(keyword, fieldNames);

    // 设置高亮
    HighlightBuilder highlightBuilder = createHighlightBuilder(fieldNames);

    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.size(10000);
    sourceBuilder.query(matchQuery).highlighter(highlightBuilder);
    SearchRequest searchRequest = new SearchRequest();
    searchRequest.source(sourceBuilder);
    searchRequest.indices(indexName);

    SearchResponse response;
    try {
      RestHighLevelClient client = elasticsearchRestTemplate.getClient();
      response = client.search(searchRequest, RequestOptions.DEFAULT);
      // 返回搜索结果
      SearchHits hits = response.getHits();

      return getHitList(hits);
    } catch (IOException e) {
      return null;
    }

  }

  /**
   * 高亮显示，返回分页
   */
  @Override
  public Page<Map<String, Object>> queryHitByPage(int pageNo, int pageSize, String keyword, String indexName,
      String... fieldNames) {
    // 构造查询条件,使用标准分词器.
    QueryBuilder matchQuery = createQueryBuilder(keyword, fieldNames);

    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.from((pageNo - 1) * pageSize);
    sourceBuilder.size(pageSize);
    sourceBuilder.query(matchQuery).highlighter(createHighlightBuilder(fieldNames));

    /// *对于text类型的字段， 默认不开启fielddata */
    // sourceBuilder.sort("id", SortOrder.ASC);
    sourceBuilder.sort("createTime", SortOrder.ASC);
    SearchRequest searchRequest = new SearchRequest();
    searchRequest.source(sourceBuilder);
    searchRequest.indices(indexName);

    SearchResponse response;
    try {
      response = client.search(searchRequest, RequestOptions.DEFAULT);
      // 返回搜索结果
      SearchHits hits = response.getHits();

      Long totalCount = hits.getTotalHits();
      Page<Map<String, Object>> page = new Page<>(pageNo, pageSize, totalCount.intValue());
      page.setList(getHitList(hits));
      return page;

    } catch (IOException e) {
      return null;
    }

  }

  /**
   * 构造查询条件
   */
  private QueryBuilder createQueryBuilder(String keyword, String... fieldNames) {
    // 构造查询条件,使用标准分词器.
    return QueryBuilders.multiMatchQuery(keyword, fieldNames) // matchQuery(),单字段搜索
        .analyzer("ik_max_word").operator(Operator.OR);
  }

  /**
   * 构造高亮器
   */
  private HighlightBuilder createHighlightBuilder(String... fieldNames) {
    // 设置高亮,使用默认的highlighter高亮器
    HighlightBuilder highlightBuilder = new HighlightBuilder()
        // .field("productName")
        .preTags("<span style='color:red'>").postTags("</span>");

    // 设置高亮字段
    for (String fieldName : fieldNames) {
      highlightBuilder.field(fieldName);
    }

    return highlightBuilder;
  }

  /**
   * 处理高亮结果
   */
  private List<Map<String, Object>> getHitList(SearchHits hits) {
    List<Map<String, Object>> list = new ArrayList<>();
    Map<String, Object> map;
    for (SearchHit searchHit : hits) {
      map = new HashMap<>();
      // 处理源数据
      map.put("source", searchHit.getSourceAsMap());
      // 处理高亮数据
      Map<String, Object> hitMap = new HashMap<>();
      searchHit.getHighlightFields().forEach((k, v) -> {
        String hight = "";
        for (Text text : v.getFragments()) {
          hight += text.string();
        }
        hitMap.put(v.getName(), hight);
      });
      map.put("highlight", hitMap);
      list.add(map);
    }
    return list;
  }

  @Override
  public void deleteIndex(String indexName) {
    elasticsearchRestTemplate.deleteIndex(indexName);

  }
}
