package com.optum.ftps.ob.core.contribution.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class StringUtilTest {

    @Test
    void constructor() throws NoSuchMethodException {
        Constructor<StringUtil> constructor = StringUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void testLoggableString() {
        // Arrange
        String input = "  test string  ";
        String expected = "test string";

        // Act
        String result = StringUtil.loggableString(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void testIsEmpty() {
        // Act & Assert
        assertTrue(StringUtil.isEmpty(""));
        assertTrue(StringUtil.isEmpty("   "));
        assertFalse(StringUtil.isEmpty("not empty"));
    }

    @Test
    void testSanitize() {
        // Arrange
        String input = "  test\nstring\r  ";
        String expected = "teststring";

        // Act
        String result = StringUtil.sanitize(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void testIsAlphanumeric() {
        // Act & Assert
        assertTrue(StringUtil.isAlphanumeric("abc123"));
        assertFalse(StringUtil.isAlphanumeric("abc 123"));
        assertFalse(StringUtil.isAlphanumeric("abc-123"));
    }

    @Test
    void testGetTrimmedString() {
        // Arrange
        String input = "  test string  ";
        String expected = "test string";

        // Act
        String result = StringUtil.getTrimmedString(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void testGetTrimmedStringUpperCase() {
        // Arrange
        String input = "  test string  ";
        String expected = "TEST STRING";

        // Act
        String result = StringUtil.getTrimmedStringUpperCase(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void testSanitizeNull() {
        Assertions.assertNull(StringUtil.sanitize(null));
    }

    @Test
    void testGetTrimmedStringNull() {
        Assertions.assertNull(StringUtil.getTrimmedString(null));
    }

    @Test
    void testGetTrimmedStringUpperCaseNull() {
        Assertions.assertNull(StringUtil.getTrimmedStringUpperCase(null));
    }

    @Test
    void loggableStringBlacnTest() {
        Assertions.assertEquals("", StringUtil.loggableString(""));
    }
}
