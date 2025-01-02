package com.optum.ftps.ob.core.contribution.util;

public class DbUtil {
    private DbUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getString(Object[] row, int index) {
        return row[index] != null ? row[index].toString().trim() : null;
    }

    public static Double getDouble(Object[] row, int index) {
        return row[index] != null ? Double.parseDouble(row[index].toString().trim()) : null;
    }

    public static Integer getInteger(Object[] row, int index) {
        return row[index] != null ? Integer.parseInt(row[index].toString().trim()) : null;
    }

    public static String getStringWithLengthCheck(Object[] row, int index, int length) {
        var value = getString(row, index);
        return value != null && value.length() >= length ? value.substring(0, length) : null;
    }
}
