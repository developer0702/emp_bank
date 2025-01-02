package com.optum.ftps.ob.core.contribution.validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.optum.ftps.ob.core.contribution.constants.ErrorCodeConstants;
import com.optum.ftps.ob.core.contribution.exceptions.ValidationException;
import com.optum.ftps.ob.core.contribution.exceptions.model.ErrorItem;
import com.optum.ftps.ob.core.contribution.service.ExceptionService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ContributionFileNamesRequestValidatorTest {
    @Mock private ExceptionService exceptionService;

    @InjectMocks
    private ContributionFileNamesRequestValidator contributionFileNamesRequestValidator;

    @Test
    void validate_ok() {
        // Arrange
        var contributionFileNames = List.of("1", "2");
        // Act
        var result = contributionFileNamesRequestValidator.validate(contributionFileNames);
        // Assert
        verify(exceptionService, times(0)).getError(anyInt());
        assertEquals(2, result.size());
    }

    @Test
    void validate_empty() {
        // Arrange
        var contributionFileNames = new ArrayList<String>();
        when(exceptionService.getError(ErrorCodeConstants.REQUIRED_FIELDS_MISSING))
                .thenReturn(ErrorItem.builder().build());
        // Act
        assertThrows(
                ValidationException.class,
                () -> contributionFileNamesRequestValidator.validate(contributionFileNames));
        // Assert
        verify(exceptionService, times(1)).getError(ErrorCodeConstants.REQUIRED_FIELDS_MISSING);
    }

    @Test
    void validate_null() {
        // Arrange
        List<String> contributionFileNames = null;
        when(exceptionService.getError(ErrorCodeConstants.REQUIRED_FIELDS_MISSING))
                .thenReturn(ErrorItem.builder().build());
        // Act
        assertThrows(
                ValidationException.class,
                () -> contributionFileNamesRequestValidator.validate(contributionFileNames));
        // Assert
        verify(exceptionService, times(1)).getError(ErrorCodeConstants.REQUIRED_FIELDS_MISSING);
    }

    @Test
    void validate_invalid() {
        // Arrange
        var contributionFileNames = List.of("AAA");
        when(exceptionService.getError(ErrorCodeConstants.INCORRECT_FORMAT))
                .thenReturn(ErrorItem.builder().build());
        // Act
        assertThrows(
                ValidationException.class,
                () -> contributionFileNamesRequestValidator.validate(contributionFileNames));
        // Assert
        verify(exceptionService, times(1)).getError(ErrorCodeConstants.INCORRECT_FORMAT);
    }

    @Test
    void validate_tooLong() {
        // Arrange
        var contributionFileNames = List.of("1234567891234567");
        when(exceptionService.getError(ErrorCodeConstants.INCORRECT_FORMAT))
                .thenReturn(ErrorItem.builder().build());
        // Act
        assertThrows(
                ValidationException.class,
                () -> contributionFileNamesRequestValidator.validate(contributionFileNames));
        // Assert
        verify(exceptionService, times(1)).getError(ErrorCodeConstants.INCORRECT_FORMAT);
    }
}
