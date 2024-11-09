package com.example.search.service;

import java.util.List;

import com.example.search.mapping.ProductDocument;


public interface EsSearchService extends BaseSearchService<ProductDocument> {

  void save(ProductDocument... productDocuments);

  /**
   * 删除
   *
   * @param id
   */
  void delete(String id);

  /**
   * 清空索引
   */
  void deleteAll();

  /**
   * 根据ID查询
   *
   * @param id
   * @return
   */
  ProductDocument getById(String id);

  /**
   * 查询全部
   *
   * @return
   */
  List<ProductDocument> getAll();
}
