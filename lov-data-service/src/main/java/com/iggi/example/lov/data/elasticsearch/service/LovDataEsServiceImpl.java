package com.iggi.example.lov.data.elasticsearch.service;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.DefaultEntityMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.iggi.example.lov.data.elasticsearch.document.EsLovData;
import com.iggi.example.lov.data.elasticsearch.repository.LovDataElasticSearchRepository;
import com.iggi.example.lov.model.LovDataRequest;
import com.iggi.example.lov.util.ElasticQueryBuilder;

@Service
public class LovDataEsServiceImpl implements LovDataEsService {

    private ElasticsearchTemplate elasticsearchTemplate;

    private LovDataElasticSearchRepository lovDataElasticSearchRepository;

    @Autowired
    private DefaultEntityMapper defaultEntityMapper;

    private static final List<EsLovData> EMPTY_LOVDATA_LIST = new ArrayList<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(LovDataEsServiceImpl.class);

    @Autowired
    public LovDataEsServiceImpl(ElasticsearchTemplate elasticsearchTemplate,
            LovDataElasticSearchRepository lovDataElasticSearchRepository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.lovDataElasticSearchRepository = lovDataElasticSearchRepository;
    }

    @Override
    public List<EsLovData> getLovDataByCriteria(LovDataRequest request) {
        if (request == null) {
            return EMPTY_LOVDATA_LIST;
        }
        LOGGER.debug("getByCriteria:{}", request);

        SearchQuery searchQuery = new ElasticQueryBuilder().build(request);

        List<EsLovData> esLovDataList = null;
        try {
            esLovDataList = elasticsearchTemplate.query(searchQuery, getResultsExtractor());

        } catch (Exception exception) {
            LOGGER.error("Exception while fetching EsLovData from Elastic", exception);
        }

        LOGGER.debug("Got search result: {} ", esLovDataList);

        return esLovDataList;
    }

    private ResultsExtractor<List<EsLovData>> getResultsExtractor() {
        return response -> {

            List<EsLovData> esLovDataList = new ArrayList<>();
            SearchHit[] hits = response.getHits()
                                       .getHits();
            for (SearchHit hit : hits) {
                try {
                    EsLovData es = defaultEntityMapper.mapToObject(hit.getSourceAsString(), EsLovData.class);
                    esLovDataList.add(es);
                } catch (Exception exception) {
                    LOGGER.error("Error while constructing EsOuMaster from ResultSet");
                }
            }

            return esLovDataList;
        };
    }

}
