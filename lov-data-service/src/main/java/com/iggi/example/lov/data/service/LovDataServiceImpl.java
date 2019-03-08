package com.iggi.example.lov.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iggi.example.lov.data.dao.LovDataDao;
import com.iggi.example.lov.data.projection.LovDataProjection;
import com.iggi.example.lov.model.LovDataRequest;

@Service
public class LovDataServiceImpl implements LovDataService {
    @Autowired
    private LovDataDao lovDataDao;

    @Override
    public List<LovDataProjection> getLovDataByLovType(LovDataRequest request) {
        return lovDataDao.getLovDataByLovType(request);
    }
}
