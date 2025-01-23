package com.optum.ftps.ob.core.employerDetails.util;

import lombok.extern.slf4j.Slf4j;

/** Number related utilities */
@Slf4j
public class NumberUtil {

    private NumberUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Integer parseInt(String value, Integer defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            log.error("Unable to parse integral value : {}", e.getMessage());
            return defaultValue;
        }
    }
}
