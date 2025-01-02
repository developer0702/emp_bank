package com.optum.ftps.ob.core.contribution.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class NumberUtilTest {

    @Test
    void constructor() throws NoSuchMethodException {
        Constructor<NumberUtil> constructor = NumberUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void testParseIntValid() {
        // Arrange
        String validInt = "123";
        Integer defaultValue = 0;

        // Act
        Integer result = NumberUtil.parseInt(validInt, defaultValue);

        // Assert
        assertEquals(123, result);
    }

    @Test
    void testParseIntInvalid() {
        // Arrange
        String invalidInt = "abc";
        Integer defaultValue = 0;

        // Act
        Integer result = NumberUtil.parseInt(invalidInt, defaultValue);

        // Assert
        assertEquals(defaultValue, result);
    }

    @Test
    void testParseIntNull() {
        // Arrange
        String nullInt = null;
        Integer defaultValue = 0;

        // Act
        Integer result = NumberUtil.parseInt(nullInt, defaultValue);

        // Assert
        assertEquals(defaultValue, result);
    }

    @Test
    void testParseIntEmptyString() {
        // Arrange
        String emptyString = "";
        Integer defaultValue = 0;

        // Act
        Integer result = NumberUtil.parseInt(emptyString, defaultValue);

        // Assert
        assertEquals(defaultValue, result);
    }
}
