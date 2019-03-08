package com.iggi.example.common.util.data;

import org.apache.commons.lang3.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

public class WhereClauseUtils {

    private WhereClauseUtils() {

    }

    public static void attributeContains(StringPath path, BooleanBuilder conditions, String value) {

        if (!StringUtils.isBlank(value)) {
            conditions.and(path.containsIgnoreCase(value));
        }
    }

    public static void attributeEquals(StringPath path, BooleanBuilder conditions, String value) {

        if (!StringUtils.isBlank(value)) {
            conditions.and(path.equalsIgnoreCase(value));
        }
    }

    public static void attributeEqualsInteger(NumberPath<Integer> path, BooleanBuilder conditions, String value) {

        if (!StringUtils.isBlank(value) && StringUtils.isNumeric(value)) {
            Integer intValue = Integer.parseInt(value);
            conditions.and(path.eq(intValue));
        }
    }

}
