package com.iggi.example.common.mapper;

import org.springframework.stereotype.Component;

@Component
public class CharSequenceMapper {
    public String asString(CharSequence value) {
        return value == null ? null : String.valueOf(value);
    }
}
