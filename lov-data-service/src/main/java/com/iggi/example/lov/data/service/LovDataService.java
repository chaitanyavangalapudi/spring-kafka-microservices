package com.iggi.example.lov.data.service;

import java.util.List;

import com.iggi.platform.exception.business.ResourceNotFoundException;
import com.iggi.example.lov.data.projection.LovDataProjection;
import com.iggi.example.lov.model.LovDataRequest;

public interface LovDataService {
    public List<LovDataProjection> getLovDataByLovType(LovDataRequest request) throws ResourceNotFoundException;
}
