package com.optum.ftps.ob.core.contribution.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class StringUtil {

    private StringUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String loggableString(String string) {
        var result = "";
        if (StringUtils.isNotBlank(string)) {
            result = sanitize(string);
        }
        return result;
    }

    public static boolean isEmpty(String argStr) {
        return StringUtils.isBlank(argStr);
    }

    public static String sanitize(String field) {
        if (null == field) {
            return null;
        }
        return field.trim().replace("\n", "").replace("\r", "");
    }

    public static boolean isAlphanumeric(String argStr) {
        if (isEmpty(argStr)) {
            return true;
        }
        var length = argStr.length();
        for (int i = 0; i < length; i++) {
            var c = argStr.charAt(i);
            if (!Character.isDigit(c) && !Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static String getTrimmedString(String data) {
        return Objects.nonNull(data) ? data.trim() : null;
    }

    public static String getTrimmedStringUpperCase(String data) {
        return Objects.nonNull(data) ? data.trim().toUpperCase() : null;
    }
}
