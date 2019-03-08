package com.iggi.example.lov.data.elasticsearch.service;

import java.util.List;

import com.iggi.example.lov.data.elasticsearch.document.EsLovData;
import com.iggi.example.lov.model.LovDataRequest;

public interface LovDataEsService {
    public List<EsLovData> getLovDataByCriteria(LovDataRequest request);
}
