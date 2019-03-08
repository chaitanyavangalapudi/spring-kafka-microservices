package com.iggi.example.lov.data.projection;

import static com.iggi.example.lov.data.entity.QLovData.lovData;

import org.springframework.stereotype.Component;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.MappingProjection;

@Component
public class LovDataProjectionFactory extends MappingProjection<LovDataProjection> {

    private static final long serialVersionUID = 4974408131743936254L;

    public LovDataProjectionFactory() {
        super(LovDataProjection.class, lovData.lovType, lovData.lovDbValue, lovData.lovDisplayValue);
    }

    @Override
    protected LovDataProjection map(Tuple row) {
        return ImmutableLovDataProjection.builder()
                                         .lovDbValue(row.get(lovData.lovDbValue))
                                         .lovDisplayValue(row.get(lovData.lovDisplayValue))
                                         .lovType(row.get(lovData.lovType))
                                         .build();
    }
}
