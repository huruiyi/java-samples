package com.example.search.repository;

import com.example.search.mapping.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;


@Component
public interface ProductDocumentRepository extends ElasticsearchRepository<ProductDocument, String> {

}
