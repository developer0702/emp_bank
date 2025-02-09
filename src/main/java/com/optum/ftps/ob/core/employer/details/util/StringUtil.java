package com.optum.ftps.ob.core.employer.details.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
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

    public static String remove(String argStr, String argRemovableChars) {
        StringBuilder sb = new StringBuilder();
        int length = argStr.length();
        for (int i = 0; i < length; i++) {
            char c = argStr.charAt(i);
            if (argRemovableChars.indexOf(c) == -1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static boolean hasWhitespace(String argStr) {
        int length = argStr.length();
        for (int i = 0; i < length; i++) {
            if (Character.isWhitespace(argStr.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumeric(String argStr) {
        if (argStr == null) {
            return false;
        }
        int length = argStr.length();
        for (int i = 0; i < length; i++) {
            char c = argStr.charAt(i);
            if (!Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkLength(String argStr, int argMin, int argMax) {
        return argStr.length() >= argMin && argStr.length() <= argMax;
    }

    public static int startsWith(String argS, String[] argPrefixes, int argOffset) {
        int length = argPrefixes.length;
        for (int i = 0; i < length; i++) {
            if (argS.startsWith(argPrefixes[i], argOffset)) {
                return i;
            }
        }
        return -1;
    }

    public static String replace(String argSource, String[] argBad, String[] argGood) {
        if (argSource == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = argSource.length();
        for (int sourceIndex = 0; sourceIndex < length; ) {
            int badIndex = startsWith(argSource, argBad, sourceIndex);
            if (badIndex == -1) {
                sb.append(argSource.charAt(sourceIndex));
                sourceIndex++;
            } else {
                sb.append(argGood[badIndex]);
                sourceIndex += argBad[badIndex].length();
            }
        }
        return sb.toString();
    }

    public static String replace(String argSource, String argBad, String argGood) {
        return replace(argSource, new String[] {argBad}, new String[] {argGood});
    }

    public static boolean contains(String argSource, String argIndex) {
        return argSource.contains(argIndex);
    }

    public static String trimSpaces(String inValue) {
        if (!isEmpty(inValue)) {
            return inValue.trim();
        }
        return inValue;
    }

    public static String formatDecimal(double value) {
        DecimalFormat decFormat = new DecimalFormat("########.##");
        return decFormat.format(value);
    }

    public static boolean isDecimal(String argStr) {
        final char PERIOD = '.';
        if (argStr == null) {
            return false;
        }
        int length = argStr.length();
        int periodCnt = 0;
        for (int i = 0; i < length; i++) {
            char c = argStr.charAt(i);
            if (!(Character.isDigit(c) || PERIOD == c)) {
                return false;
            }
            if (PERIOD == c) {
                periodCnt++;
            }
        }
        String decValue = "";
        int index = argStr.indexOf(PERIOD);
        if (index != -1 && argStr.length() > index + 1) {
            decValue = argStr.substring(index + 1);
        }
        return periodCnt <= 1 && (!decValue.isEmpty() || index == -1) && decValue.length() <= 2;
    }

    public static String removeSpecialChar(String inString) {
        int A = 'A';
        int Z = 'Z';
        int a = 'a';
        int z = 'z';
        int SPACE = ' ';
        int ZERO = '0';
        int NINE = '9';
        char[] charArray = inString.toCharArray();
        StringBuilder returnString = new StringBuilder(inString.length());
        int size = charArray.length;
        for (int i = 0; i < size; i++) {
            char character = charArray[i];
            if ((character >= A && character <= Z)
                    || (character >= a && character <= z)
                    || character == SPACE
                    || (character >= ZERO && character <= NINE)) {
                returnString.append(character);
            }
        }
        return returnString.toString().trim();
    }

    public static String splitBySpecialChars(String string) {
        if (string != null && !string.isEmpty()) {
            String[] tokens = string.split("[^a-zA-Z0-9]");
            if (tokens.length > 0) {
                return tokens[0].toUpperCase();
            }
        }

        return string.toUpperCase();
    }

    public static String getStringWithMaxLength(String data, int length) {
        if (data != null && data.length() > length) {
            return data.substring(0, length);
        }
        return data;
    }
}
