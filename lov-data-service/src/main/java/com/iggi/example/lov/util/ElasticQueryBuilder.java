package com.iggi.example.lov.util;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import com.iggi.example.common.util.CustomBeanUtils;
import com.iggi.example.lov.model.LovDataRequest;

@Component
public class ElasticQueryBuilder {

    public static final Logger LOGGER = LoggerFactory.getLogger(ElasticQueryBuilder.class);

    private static final FieldSortBuilder lovTypeSorter = new FieldSortBuilder("lovType").order(SortOrder.ASC);

    private static final FieldSortBuilder priorityNumberSorter = new FieldSortBuilder(
            "sequenceNumber").unmappedType("integer")
                             .order(SortOrder.ASC);

    private static final String[] fieldList = { "lovType", "lovDbValue", "lovDisplayValue", "id" };

    BoolQueryBuilder conditions = null;

    public void init() {
        conditions = QueryBuilders.boolQuery();
    }

    public SearchQuery build(LovDataRequest request) {

        SearchQuery searchQuery = null;
        if (request != null) {
            init();
            Map<String, Object> requestBeanMap = CustomBeanUtils.getPropertyValueMap(request);
            LOGGER.debug("Request Bean Map built: {}", requestBeanMap);
            requestBeanMap.forEach(this::addConditions);
            searchQuery = new NativeSearchQueryBuilder().withQuery(conditions)
                                                        .withFields(fieldList)
                                                        .withSort(lovTypeSorter)
                                                        .withSort(priorityNumberSorter)
                                                        .withPageable(PageRequest.of(0, 100))
                                                        .build();
            LOGGER.debug("Search Query built: {}", searchQuery.getQuery());
        }

        return searchQuery;

    }

    private void addConditions(String column, Object valueObject) {
        String value = String.valueOf(valueObject)
                             .trim();

        if (Objects.isNull(valueObject) || StringUtils.isBlank(value)) {
            return;
        }

        conditions.must(QueryBuilders.queryStringQuery(value)
                                     .lenient(true)
                                     .field(column));
    }
}
