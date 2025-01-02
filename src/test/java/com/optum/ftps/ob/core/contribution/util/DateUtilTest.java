package com.optum.ftps.ob.core.contribution.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

class DateUtilTest {

    @Test
    void testParse_ValidDate() {
        // Arrange
        String dateStr = "2023-10-01";
        String format = "yyyy-MM-dd";

        // Act
        Date result = DateUtil.parse(dateStr, format);

        // Assert
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        assertEquals(dateStr, dateFormat.format(result));
    }

    @Test
    void testParse_InvalidDate() {
        // Arrange
        String dateStr = "invalid-date";
        String format = "yyyy-MM-dd";

        // Act
        Date result = DateUtil.parse(dateStr, format);

        // Assert
        assertNull(result);
    }

    @Test
    void testFormat() {
        // Arrange
        String format = "yyyy-MM-dd";
        Date date = new Date(); // Fixed date for testing

        // Act
        String result = DateUtil.format(date, format);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testGetCurrentDate() {
        // Act
        Date result = DateUtil.getCurrentDate();

        // Assert
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String expectedDate = dateFormat.format(new Date());
        assertEquals(expectedDate, dateFormat.format(result));
    }

    @Test
    void constructor() throws NoSuchMethodException {
        Constructor<DateUtil> constructor = DateUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }
}
