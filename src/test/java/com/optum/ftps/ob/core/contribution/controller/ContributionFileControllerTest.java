package com.optum.ftps.ob.core.contribution.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileHistoryResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileNamesDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileNamesResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileTransactionResponseDTO;
import com.optum.ftps.ob.core.contribution.exceptions.ValidationException;
import com.optum.ftps.ob.core.contribution.mapper.ContributionFileHistoryResponseMapper;
import com.optum.ftps.ob.core.contribution.mapper.ContributionFileNamesResponseMapper;
import com.optum.ftps.ob.core.contribution.mapper.ContributionFileTransactionResponseMapper;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionFileHistoryResponse;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionFileNamesResponse;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionFileTransactionResponse;
import com.optum.ftps.ob.core.contribution.service.ContributionFileService;
import com.optum.ftps.ob.core.contribution.service.ExceptionService;
import com.optum.ftps.ob.core.contribution.validator.ContributionFileHistoryValidator;
import com.optum.ftps.ob.core.contribution.validator.ContributionFileNamesRequestValidator;
import com.optum.ftps.ob.core.contribution.validator.ContributionFileTransactionValidator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ContributionFileControllerTest {

    @Mock private ContributionFileService contributionFileService;
    @Mock private ContributionFileNamesResponseMapper contributionFileNamesResponseMapper;
    @Mock private ContributionFileNamesRequestValidator contributionFileNamesRequestValidator;
    @Mock private ContributionFileHistoryValidator contributionFileHistoryValidator;
    @Mock private ContributionFileHistoryResponseMapper contributionFileHistoryResponseMapper;
    @Mock private ExceptionService exceptionService;

    @Mock
    private ContributionFileTransactionResponseMapper contributionFileTransactionResponseMapper;

    @Mock private ContributionFileTransactionValidator contributionFileTransactionValidator;

    @InjectMocks private ContributionFileController contributionFileController;

    private static final ContributionFileNamesDTO mockContributionFileNamesDTO =
            new ContributionFileNamesDTO(null, null, null, null, null, null);

    @Test
    void testGetEmployerContributionFileHistory_ValidInput() {
        // Arrange
        String customerPolicyNumber = "12345";
        String effectiveTransactionDate = "2023-01-01";

        ContributionFileHistoryResponseDTO responseDTO = new ContributionFileHistoryResponseDTO();
        ContributionFileHistoryResponse response = new ContributionFileHistoryResponse();

        when(contributionFileHistoryValidator.validateContributionFileHistory(
                        customerPolicyNumber, effectiveTransactionDate))
                .thenReturn(Collections.emptyList());
        when(contributionFileService.getEmployerContributionFileHistory(
                        customerPolicyNumber, effectiveTransactionDate))
                .thenReturn(responseDTO);
        when(contributionFileHistoryResponseMapper.contributionFileHistoryResponse(responseDTO))
                .thenReturn(response);

        // Act
        ResponseEntity<ContributionFileHistoryResponse> result =
                contributionFileController.getEmployerContributionFileHistory(
                        customerPolicyNumber, effectiveTransactionDate);

        // Assert
        assertEquals(response, result.getBody());
    }

    @Test
    void testGetEmployerContributionFileHistory_WithErrors() {
        // Arrange
        String customerPolicyNumber = "12345";
        String effectiveTransactionDate = "2023-01-01";

        List<Integer> errors = Collections.singletonList(1);

        when(contributionFileHistoryValidator.validateContributionFileHistory(
                        customerPolicyNumber, effectiveTransactionDate))
                .thenReturn(errors);

        // Act & Assert
        try {
            contributionFileController.getEmployerContributionFileHistory(
                    customerPolicyNumber, effectiveTransactionDate);
        } catch (ValidationException e) {
            assertEquals(1, e.getErrors().size());
        }
    }

    @Test
    void getEmployerContributionFileNames_ok() {
        // Arrange
        var contributionFilePortalId = List.of("1", "2");
        var contributionFileNamesResponseDTO = new ContributionFileNamesResponseDTO();
        contributionFileNamesResponseDTO.setContributionFileNames(
                List.of(mockContributionFileNamesDTO));
        when(contributionFileNamesRequestValidator.validate(contributionFilePortalId))
                .thenReturn(contributionFilePortalId);
        when(contributionFileService.getContributionFileNames(contributionFilePortalId))
                .thenReturn(contributionFileNamesResponseDTO);
        var contributionFileNamesResponse = new ContributionFileNamesResponse();
        when(contributionFileNamesResponseMapper.mapContributionFileNamesResponse(
                        contributionFileNamesResponseDTO))
                .thenReturn(contributionFileNamesResponse);

        // Act
        var result =
                contributionFileController.getEmployerContributionFileNames(
                        contributionFilePortalId);

        // Assert
        assertEquals(contributionFileNamesResponse, result.getBody());
        verify(contributionFileNamesRequestValidator, times(1)).validate(contributionFilePortalId);
        verify(contributionFileService, times(1))
                .getContributionFileNames(contributionFilePortalId);
        verify(contributionFileNamesResponseMapper, times(1))
                .mapContributionFileNamesResponse(contributionFileNamesResponseDTO);
    }

    @Test
    void getEmployerContributionFileNames_validationError() {
        // Arrange
        var contributionFilePortalId = List.of("AAA");
        when(contributionFileNamesRequestValidator.validate(contributionFilePortalId))
                .thenThrow(ValidationException.class);

        // Act
        assertThrows(
                ValidationException.class,
                () ->
                        contributionFileController.getEmployerContributionFileNames(
                                contributionFilePortalId));

        // Assert
        verify(contributionFileNamesRequestValidator, times(1)).validate(contributionFilePortalId);
        verify(contributionFileService, times(0))
                .getContributionFileNames(contributionFilePortalId);
        verify(contributionFileNamesResponseMapper, times(0))
                .mapContributionFileNamesResponse(any(ContributionFileNamesResponseDTO.class));
    }

    @Test
    void getEmployerContributionFileNames_empty() {
        // Arrange
        var contributionFilePortalId = Collections.<String>emptyList();
        when(contributionFileNamesRequestValidator.validate(contributionFilePortalId))
                .thenThrow(ValidationException.class);

        // Act
        assertThrows(
                ValidationException.class,
                () ->
                        contributionFileController.getEmployerContributionFileNames(
                                contributionFilePortalId));

        // Assert
        verify(contributionFileNamesRequestValidator, times(1)).validate(contributionFilePortalId);
        verify(contributionFileService, times(0))
                .getContributionFileNames(contributionFilePortalId);
        verify(contributionFileNamesResponseMapper, times(0))
                .mapContributionFileNamesResponse(any(ContributionFileNamesResponseDTO.class));
    }

    @Test
    void testGetEmployerContributionFileTransactions_ValidInput() {
        // Arrange
        String contributionFilePortalId = "12345";
        ContributionFileTransactionResponseDTO responseDTO =
                new ContributionFileTransactionResponseDTO();
        ContributionFileTransactionResponse response = new ContributionFileTransactionResponse();

        when(contributionFileTransactionValidator.validateContributionFileTransaction(
                        contributionFilePortalId))
                .thenReturn(Collections.emptyList());
        when(contributionFileService.getEmployerContributionFileTransactions(
                        contributionFilePortalId))
                .thenReturn(responseDTO);
        when(contributionFileTransactionResponseMapper.contributionFileTransactionResponse(
                        responseDTO))
                .thenReturn(response);

        // Act
        ResponseEntity<ContributionFileTransactionResponse> result =
                contributionFileController.getEmployerContributionFileTransactions(
                        contributionFilePortalId);

        // Assert
        assertEquals(response, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void testGetEmployerContributionFileTransactions_WithErrors() {
        // Arrange
        String contributionFilePortalId = "12345";

        doThrow(ValidationException.class)
                .when(contributionFileTransactionValidator)
                .validateContributionFileTransaction(contributionFilePortalId);

        // Act & Assert
        assertThrows(
                ValidationException.class,
                () ->
                        contributionFileController.getEmployerContributionFileTransactions(
                                contributionFilePortalId));
    }
}
