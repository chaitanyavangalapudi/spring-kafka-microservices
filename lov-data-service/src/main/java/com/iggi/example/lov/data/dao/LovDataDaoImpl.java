package com.iggi.example.lov.data.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iggi.platform.exception.business.ResourceNotFoundException;
import com.iggi.example.lov.data.entity.QLovData;
import com.iggi.example.lov.data.projection.LovDataProjection;
import com.iggi.example.lov.data.projection.LovDataProjectionFactory;
import com.iggi.example.lov.model.LovDataRequest;
import com.iggi.example.lov.util.WhereClauseBuilder;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
public class LovDataDaoImpl implements LovDataDao {
    public static final Logger LOGGER = LoggerFactory.getLogger(LovDataDaoImpl.class);

    private JPAQueryFactory queryFactory;

    private LovDataProjectionFactory lovDataProjectionFactory;

    @Autowired
    public LovDataDaoImpl(JPAQueryFactory queryFactory, LovDataProjectionFactory lovDataProjectionFactory) {
        super();
        this.queryFactory = queryFactory;
        this.lovDataProjectionFactory = lovDataProjectionFactory;
    }

    @Override
    public List<LovDataProjection> getLovDataByLovType(LovDataRequest request) {
        if (StringUtils.isBlank(request.getLovType())) {
            return null;
        }

        List<LovDataProjection> lovDataList = null;
        BooleanBuilder conditions = null;
        QLovData lovData = QLovData.lovData;

        try {
            conditions = new WhereClauseBuilder().build(request);
            lovDataList = queryFactory.select(lovDataProjectionFactory)
                                       .from(lovData)
                                       .where(conditions.getValue())
                                       .orderBy(orderBySequenceNumber())
                                       .fetch();
        } catch (Exception exception) {
            LOGGER.error("Error while fetching LovData for :{}", request);
        }
        if (lovDataList == null || lovDataList.isEmpty()) {
            String message = String.format(
                    "Unable to fetch Lov Data from Database for '%s' , please contact support team",
                    request.getLovType());
            throw new ResourceNotFoundException(message);
        }

        return lovDataList;
    }

    private OrderSpecifier<Integer> orderBySequenceNumber() {
        return QLovData.lovData.sequenceNumber.asc();
    }
}
