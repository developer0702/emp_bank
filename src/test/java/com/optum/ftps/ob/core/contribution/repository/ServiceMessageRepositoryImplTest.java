package com.optum.ftps.ob.core.contribution.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusMessageType;
import com.optum.ftps.ob.core.contribution.repository.impl.ServiceMessageRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class ServiceMessageRepositoryImplTest {

    @Mock private EntityManager entityManager;

    @Mock private Query query;

    @InjectMocks private ServiceMessageRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStatusMessageType() {
        // Arrange
        int statusCode = 200;
        List<Object[]> rows = new ArrayList<>();
        rows.add(new Object[] {"200", "Request Successfully Processed", "INFO"});
        when(entityManager.createNativeQuery(any())).thenReturn(query);
        when(query.setParameter("statusCode", statusCode)).thenReturn(query);
        when(query.getResultList()).thenReturn(rows);

        // Act
        StatusMessageType result = repository.getStatusMessageType(statusCode);

        // Assert
        assertEquals("200", result.getStatusCode());
        assertEquals("Request Successfully Processed", result.getStatusDescription());
        assertEquals("INFO", result.getSeverity());
    }

    @Test
    void testGetStatusMessageType_NoResult() {
        // Arrange
        int statusCode = 404;
        when(entityManager.createNativeQuery(any())).thenReturn(query);
        when(query.setParameter("statusCode", statusCode)).thenReturn(query);
        when(query.getResultList()).thenReturn(new ArrayList<>());

        // Act
        StatusMessageType result = repository.getStatusMessageType(statusCode);

        // Assert
        assertNull(result);
    }

    @Test
    void testMapServiceMessageResult() {
        // Arrange
        List<Object[]> queryResult = new ArrayList<>();
        queryResult.add(new Object[] {"200", "Request Successfully Processed", "INFO"});

        // Act
        StatusMessageType result =
                ServiceMessageRepositoryImpl.mapServiceMessageResult(queryResult);

        // Assert
        assertEquals("200", result.getStatusCode());
        assertEquals("Request Successfully Processed", result.getStatusDescription());
        assertEquals("INFO", result.getSeverity());
    }

    @Test
    void testMapServiceMessageResult_Empty() {
        // Arrange
        List<Object[]> queryResult = new ArrayList<>();

        // Act
        StatusMessageType result =
                ServiceMessageRepositoryImpl.mapServiceMessageResult(queryResult);

        // Assert
        assertNull(result);
    }
}
