package com.example.search.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.example.search.mapping.ProductDocument;
import com.example.search.repository.ProductDocumentRepository;
import com.example.search.service.EsSearchService;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

@Service
public class EsSearchServiceImpl extends BaseSearchServiceImpl<ProductDocument> implements EsSearchService {

  @Resource
  private ElasticsearchRestTemplate elasticsearchRestTemplate;

  @Resource
  private ProductDocumentRepository productDocumentRepository;

  @Override
  public void save(ProductDocument... productDocuments) {
    elasticsearchRestTemplate.putMapping(ProductDocument.class);
    if (productDocuments.length > 0) {
      productDocumentRepository.saveAll(Arrays.asList(productDocuments));
    }
  }

  @Override
  public void delete(String id) {
    productDocumentRepository.deleteById(id);
  }

  @Override
  public void deleteAll() {
    productDocumentRepository.deleteAll();
  }

  @Override
  public ProductDocument getById(String id) {
    return productDocumentRepository.findById(id).get();
  }

  @Override
  public List<ProductDocument> getAll() {
    List<ProductDocument> list = new ArrayList<>();
    productDocumentRepository.findAll().forEach(list::add);
    return list;
  }

}
