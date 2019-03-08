package com.iggi.example.lov.data.dao;

import java.util.List;

import com.iggi.platform.exception.business.ResourceNotFoundException;
import com.iggi.example.lov.data.projection.LovDataProjection;
import com.iggi.example.lov.model.LovDataRequest;

public interface LovDataDao {
    public List<LovDataProjection> getLovDataByLovType(LovDataRequest request) throws ResourceNotFoundException;

}
