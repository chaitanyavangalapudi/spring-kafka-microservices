package com.iggi.example.lov.data.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.iggi.example.lov.data.elasticsearch.document.EsLovData;

@Repository
public interface LovDataElasticSearchRepository extends ElasticsearchRepository<EsLovData, Integer> {
}
