package com.iggi.example.common.util;

import org.apache.commons.lang3.StringUtils;

public class CommonUtils {

    private CommonUtils() {

    }

    public static String getNonNullTrimmedString(String str) {
        return StringUtils.defaultString(str)
                          .trim();
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return StringUtils.equalsIgnoreCase(getNonNullTrimmedString(str1), getNonNullTrimmedString(str2));
    }
}
