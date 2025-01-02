package com.optum.ftps.ob.core.contribution.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class DbUtilTest {

    @Test
    void testGetStringWithLengthCheck_ValidInput() {
        // Arrange
        Object[] row = {"testString"};
        int index = 0;
        int length = 4;

        // Act
        String result = DbUtil.getStringWithLengthCheck(row, index, length);

        // Assert
        assertEquals("test", result);
    }

    @Test
    void testGetStringWithLengthCheck_InputShorterThanLength() {
        // Arrange
        Object[] row = {"test"};
        int index = 0;
        int length = 10;

        // Act
        String result = DbUtil.getStringWithLengthCheck(row, index, length);

        // Assert
        assertNull(result);
    }

    @Test
    void testGetStringWithLengthCheck_NullInput() {
        // Arrange
        Object[] row = {null};
        int index = 0;
        int length = 4;

        // Act
        String result = DbUtil.getStringWithLengthCheck(row, index, length);

        // Assert
        assertNull(result);
    }

    @Test
    void testGetString_ValidInput() {
        // Arrange
        Object[] row = {"testString"};
        int index = 0;

        // Act
        String result = DbUtil.getString(row, index);

        // Assert
        assertEquals("testString", result);
    }

    @Test
    void testGetString_NullInput() {
        // Arrange
        Object[] row = {null};
        int index = 0;

        // Act
        String result = DbUtil.getString(row, index);

        // Assert
        assertNull(result);
    }

    @Test
    void testGetDouble_ValidInput() {
        // Arrange
        Object[] row = {"123.45"};
        int index = 0;

        // Act
        Double result = DbUtil.getDouble(row, index);

        // Assert
        assertEquals(123.45, result);
    }

    @Test
    void testGetDouble_NullInput() {
        // Arrange
        Object[] row = {null};
        int index = 0;

        // Act
        Double result = DbUtil.getDouble(row, index);

        // Assert
        assertNull(result);
    }

    @Test
    void testGetDouble_InvalidInput() {
        // Arrange
        Object[] row = {"invalid"};
        int index = 0;

        // Act & Assert
        assertThrows(NumberFormatException.class, () -> DbUtil.getDouble(row, index));
    }

    @Test
    void testGetInteger_ValidInput() {
        // Arrange
        Object[] row = {"123"};
        int index = 0;

        // Act
        Integer result = DbUtil.getInteger(row, index);

        // Assert
        assertEquals(123, result);
    }

    @Test
    void testGetInteger_NullInput() {
        // Arrange
        Object[] row = {null};
        int index = 0;

        // Act
        Integer result = DbUtil.getInteger(row, index);

        // Assert
        assertNull(result);
    }

    @Test
    void testGetInteger_InvalidInput() {
        // Arrange
        Object[] row = {"invalid"};
        int index = 0;

        // Act & Assert
        assertThrows(NumberFormatException.class, () -> DbUtil.getInteger(row, index));
    }

    @Test
    void constructor() throws NoSuchMethodException {
        Constructor<DbUtil> constructor = DbUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }
}
