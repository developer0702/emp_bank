package com.optum.ftps.ob.core.contribution.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.optum.ftps.ob.core.contribution.data.StatusMessageTypeData;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.StatusMessageType;
import com.optum.ftps.ob.core.contribution.exceptions.ValidationException;
import com.optum.ftps.ob.core.contribution.exceptions.model.ErrorItem;
import com.optum.ftps.ob.core.contribution.mapper.ErrorItemMapper;
import com.optum.ftps.ob.core.contribution.repository.ServiceMessageTypeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ExceptionServiceImplTest {

    @Mock private ErrorItemMapper errorItemMapper;

    @Mock private ServiceMessageTypeRepository serviceMessageTypeRepository;

    @InjectMocks @Spy private ExceptionServiceImpl exceptionService;

    @BeforeEach
    void setUp() {
        exceptionService = new ExceptionServiceImpl(errorItemMapper, serviceMessageTypeRepository);
    }

    @Test
    void testGetError_CacheMiss() {
        // Arrange
        int errorCode = 404;
        StatusMessageType statusMessageType = StatusMessageType.builder().build();
        statusMessageType.setStatusCode("404");
        statusMessageType.setStatusDescription("Not Found");
        statusMessageType.setSeverity("ERROR");

        ErrorItem errorItem = ErrorItem.builder().build();
        errorItem.setStatusCode("404");
        errorItem.setStatusDescription("Not Found");

        when(serviceMessageTypeRepository.getStatusMessageType(errorCode))
                .thenReturn(statusMessageType);
        when(errorItemMapper.map(statusMessageType)).thenReturn(errorItem);

        // Act
        ErrorItem result = exceptionService.getError(errorCode);

        // Assert
        assertEquals(errorItem, result);
    }

    @Test
    void testGetError_CacheHit() {
        // Arrange
        int errorCode = 404;
        StatusMessageType statusMessageType = StatusMessageType.builder().build();
        statusMessageType.setStatusCode("404");
        statusMessageType.setStatusDescription("Not Found");
        statusMessageType.setSeverity("ERROR");

        ErrorItem errorItem = ErrorItem.builder().build();
        errorItem.setStatusCode("404");
        errorItem.setStatusDescription("Not Found");

        when(serviceMessageTypeRepository.getStatusMessageType(errorCode))
                .thenReturn(statusMessageType);
        when(errorItemMapper.map(statusMessageType)).thenReturn(errorItem);

        // First call to populate the cache
        exceptionService.getError(errorCode);

        // Act
        ErrorItem result = exceptionService.getError(errorCode);

        // Assert
        assertEquals(errorItem, result);
    }

    @Test
    void testHandleValidationError_WithErrors() {
        // Arrange
        int errorCode = 404;
        List<Integer> codes = Collections.singletonList(errorCode);
        ErrorItem errorItem =
                ErrorItem.builder().statusCode("404").statusDescription("Not Found").build();

        when(serviceMessageTypeRepository.getStatusMessageType(errorCode))
                .thenReturn(StatusMessageTypeData.getStatusMessageType());
        lenient()
                .when(errorItemMapper.map(StatusMessageType.builder().build()))
                .thenReturn(errorItem);

        // Act & Assert
        assertThrows(
                ValidationException.class, () -> exceptionService.handleValidationError(codes));
    }

    @Test
    void testHandleValidationError_EmptyCodes() {
        ExceptionServiceImpl service = Mockito.spy(exceptionService);
        // Arrange
        List<Integer> codes = Collections.emptyList();

        // Act
        service.handleValidationError(codes);

        // Assert
        // No exception should be thrown
        Mockito.verify(service, times(0)).getError(any());
    }
}
