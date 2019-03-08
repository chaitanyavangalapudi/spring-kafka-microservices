package com.iggi.example.common.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public CharSequence asCharSequence(LocalDateTime time) {
        return (time != null) ? time.format(formatter) : null;
    }

    public LocalDateTime asLocalDateTime(CharSequence time) {
        if (StringUtils.isBlank(time)) {
            return null;
        }

        try {
            LocalDateTime javaTime = LocalDateTime.parse(time, formatter);
            return javaTime;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

}
