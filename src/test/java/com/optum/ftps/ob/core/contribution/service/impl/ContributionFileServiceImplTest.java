package com.optum.ftps.ob.core.contribution.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileHistoryResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileNamesDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusDbDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusResponseDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileTransactionResponseDTO;
import com.optum.ftps.ob.core.contribution.exceptions.ContributionFileStatusException;
import com.optum.ftps.ob.core.contribution.exceptions.NotFoundException;
import com.optum.ftps.ob.core.contribution.exceptions.ServiceException;
import com.optum.ftps.ob.core.contribution.exceptions.model.ErrorItem;
import com.optum.ftps.ob.core.contribution.repository.ContributionFileRepository;
import com.optum.ftps.ob.core.contribution.repository.ServiceMessageTypeRepository;
import com.optum.ftps.ob.core.contribution.service.ExceptionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ContributionFileServiceImplTest {

    @Mock private ContributionFileRepository contributionFileRepository;
    @Mock private ServiceMessageTypeRepository serviceMessageTypeRepository;
    @Mock private ExceptionService exceptionService;

    @InjectMocks private ContributionFileServiceImpl contributionFileService;

    private ErrorItem errorItem;
    private ContributionFileStatusDTO contributionFileStatusDTO;
    private static final ContributionFileNamesDTO mockContributionFileNamesDTO =
            new ContributionFileNamesDTO(null, null, null, null, null, null);

    @BeforeEach
    void setUp() {
        contributionFileStatusDTO =
                new ContributionFileStatusDTO(12345, 67890, null, null, null, "A");
        errorItem =
                ErrorItem.builder()
                        .statusCode("500")
                        .statusDescription("Service Failed")
                        .severity("ERROR")
                        .build();
    }

    @Test
    void testGetEmployerContributionFileHistory_ValidInput() {
        // Arrange
        String customerPolicyNumber = "12345";
        String effectiveTransactionDate = "2023-01-01";

        when(contributionFileRepository.getEmployerContributionFileHistory(any(), any()))
                .thenReturn(Collections.emptyList());

        // Act
        ContributionFileHistoryResponseDTO response =
                contributionFileService.getEmployerContributionFileHistory(
                        customerPolicyNumber, effectiveTransactionDate);

        // Assert
        assertEquals(0, response.getContributionFileHistoryRecords().size());
    }

    @Test
    void getContributionFileNames_ok() {
        // Arrange
        var contributionFileNamesDTOs = List.of(mockContributionFileNamesDTO);
        when(contributionFileRepository.getContributionFileNames(List.of("1", "2")))
                .thenReturn(contributionFileNamesDTOs);
        // Act
        var result = contributionFileService.getContributionFileNames(List.of("1", "2"));
        // Assert
        assertEquals(contributionFileNamesDTOs, result.getContributionFileNames());
    }

    @Test
    void testGetEmployerContributionFileTransactions_ValidInput() {
        // Arrange
        String contributionFilePortalId = "12345";
        when(contributionFileRepository.getEmployerContributionFileTransactions(any()))
                .thenReturn(Collections.emptyList());

        // Act
        ContributionFileTransactionResponseDTO response =
                contributionFileService.getEmployerContributionFileTransactions(
                        contributionFilePortalId);

        // Assert
        assertEquals(0, response.getContributionFiles().size());
    }

    @Test
    void testCheckExistingStatus_StatusPending() {
        // Arrange
        ContributionFileStatusDbDTO dbDTO =
                ContributionFileStatusDbDTO.builder().statusCode("P").build();
        doReturn(List.of(dbDTO))
                .when(contributionFileRepository)
                .getContributionFileStatus(contributionFileStatusDTO);

        // Act
        contributionFileService.checkExistingStatus(contributionFileStatusDTO);

        // Assert
        verify(contributionFileRepository).getContributionFileStatus(contributionFileStatusDTO);
    }

    @Test
    void testCheckExistingStatus_StatusNotPending() {
        // Arrange
        ContributionFileStatusDbDTO dbDTO =
                ContributionFileStatusDbDTO.builder().statusCode("APPROVED").build();
        doReturn(List.of(dbDTO))
                .when(contributionFileRepository)
                .getContributionFileStatus(contributionFileStatusDTO);

        // Act & Assert
        assertThrows(
                ContributionFileStatusException.class,
                () -> contributionFileService.checkExistingStatus(contributionFileStatusDTO));
    }

    @Test
    void testCheckExistingStatus_EmptyStatusList() {
        // Arrange
        doReturn(Collections.emptyList())
                .when(contributionFileRepository)
                .getContributionFileStatus(contributionFileStatusDTO);

        // Act
        contributionFileService.checkExistingStatus(contributionFileStatusDTO);

        // Assert
        verify(contributionFileRepository).getContributionFileStatus(contributionFileStatusDTO);
    }

    @Test
    void testCheckFundStatus_ValidFundId() {
        // Arrange
        doReturn(true).when(contributionFileRepository).isValidFundId(contributionFileStatusDTO);

        // Act
        contributionFileService.checkFundStatus(contributionFileStatusDTO);

        // Assert
        verify(contributionFileRepository).isValidFundId(contributionFileStatusDTO);
    }

    @Test
    void testCheckFundStatus_InvalidFundId() {
        // Arrange
        doReturn(false).when(contributionFileRepository).isValidFundId(contributionFileStatusDTO);

        // Act & Assert
        assertThrows(
                ContributionFileStatusException.class,
                () -> contributionFileService.checkFundStatus(contributionFileStatusDTO));
    }

    @Test
    void testHandleDenialRequest_Success() {
        // Arrange
        when(contributionFileRepository.denyContributionFile(contributionFileStatusDTO))
                .thenReturn(1);

        // Act
        int result = contributionFileService.handleDenialRequest(contributionFileStatusDTO);

        // Assert
        assertEquals(1, result);
        verify(contributionFileRepository).denyContributionFile(contributionFileStatusDTO);
    }

    @Test
    void testHandleDenialRequest_Failure() {
        // Arrange
        doThrow(new RuntimeException())
                .when(contributionFileRepository)
                .denyContributionFile(contributionFileStatusDTO);
        when(exceptionService.getError(any())).thenReturn(errorItem);

        // Act & Assert
        assertThrows(
                ServiceException.class,
                () -> contributionFileService.handleDenialRequest(contributionFileStatusDTO));
        verify(contributionFileRepository).denyContributionFile(contributionFileStatusDTO);
    }

    @Test
    void testHandleApprovalRequest_Success() {
        // Arrange
        when(contributionFileRepository.approveContributionFile(contributionFileStatusDTO))
                .thenReturn(1);
        // TODO: redo when the table is migrated in new schema
        // when(contributionFileRepository.isValidFundId(any())).thenReturn(true);

        // Act
        int result = contributionFileService.handleApprovalRequest(contributionFileStatusDTO);

        // Assert
        assertEquals(1, result);
        verify(contributionFileRepository).approveContributionFile(contributionFileStatusDTO);
    }

    @Test
    void testHandleApprovalRequest_Failure() {
        // Arrange
        // TODO: redo when the table is migrated in new schema
        // doReturn(true).when(contributionFileRepository).isValidFundId(contributionFileStatusDTO);
        doThrow(new RuntimeException())
                .when(contributionFileRepository)
                .approveContributionFile(contributionFileStatusDTO);
        when(exceptionService.getError(any())).thenReturn(errorItem);

        // Act & Assert
        assertThrows(
                Exception.class,
                () -> contributionFileService.handleApprovalRequest(contributionFileStatusDTO));
        verify(contributionFileRepository).approveContributionFile(contributionFileStatusDTO);
    }

    @Test
    void testUpdateContributionFileStatus_Success() {
        // Arrange
        ContributionFileStatusDbDTO pendingDto =
                ContributionFileStatusDbDTO.builder().statusCode("P").build();
        doReturn(Collections.singletonList(pendingDto))
                .when(contributionFileRepository)
                .getContributionFileStatus(contributionFileStatusDTO);
        // TODO: redo when the table is migrated in new schema
        // doReturn(true).when(contributionFileRepository).isValidFundId(contributionFileStatusDTO);
        when(contributionFileRepository.approveContributionFile(contributionFileStatusDTO))
                .thenReturn(1);

        // Act
        ContributionFileStatusResponseDTO response =
                contributionFileService.updateContributionFileStatus(contributionFileStatusDTO);

        // Assert
        assertEquals(12345, response.getContributionFileId());
        verify(contributionFileRepository).getContributionFileStatus(contributionFileStatusDTO);
        verify(contributionFileRepository).approveContributionFile(contributionFileStatusDTO);
    }

    @Test
    void testUpdateContributionFileStatus_NotFound() {
        // Arrange
        ContributionFileStatusDbDTO dto =
                ContributionFileStatusDbDTO.builder().statusCode("P").build();
        doReturn(Collections.singletonList(dto))
                .when(contributionFileRepository)
                .getContributionFileStatus(contributionFileStatusDTO);
        // TODO: redo when the table is migrated in new schema
        // doReturn(true).when(contributionFileRepository).isValidFundId(contributionFileStatusDTO);
        when(contributionFileRepository.approveContributionFile(contributionFileStatusDTO))
                .thenReturn(0);
        when(exceptionService.getError(any())).thenReturn(errorItem);

        // Act & Assert
        assertThrows(
                NotFoundException.class,
                () ->
                        contributionFileService.updateContributionFileStatus(
                                contributionFileStatusDTO));
        verify(contributionFileRepository).getContributionFileStatus(contributionFileStatusDTO);
        verify(contributionFileRepository).approveContributionFile(contributionFileStatusDTO);
    }

    @Test
    void testUpdateContributionFileStatus_StatusNotPending() {
        // Arrange
        ContributionFileStatusDbDTO dto =
                ContributionFileStatusDbDTO.builder().statusCode("A").build();

        doReturn(Collections.singletonList(dto))
                .when(contributionFileRepository)
                .getContributionFileStatus(contributionFileStatusDTO);

        // Act & Assert
        assertThrows(
                ContributionFileStatusException.class,
                () ->
                        contributionFileService.updateContributionFileStatus(
                                contributionFileStatusDTO));
        verify(contributionFileRepository).getContributionFileStatus(contributionFileStatusDTO);
    }

    @Test
    void testUpdateContributionFileStatus_ServiceFailed() {
        // Arrange
        ContributionFileStatusDbDTO dto =
                ContributionFileStatusDbDTO.builder().statusCode("P").build();
        doReturn(Collections.singletonList(dto))
                .when(contributionFileRepository)
                .getContributionFileStatus(contributionFileStatusDTO);

        // TODO: redo when the table is migrated in new schema
        // doReturn(true).when(contributionFileRepository).isValidFundId(contributionFileStatusDTO);
        doThrow(new RuntimeException())
                .when(contributionFileRepository)
                .approveContributionFile(contributionFileStatusDTO);
        when(exceptionService.getError(any())).thenReturn(errorItem);

        // Act & Assert
        assertThrows(
                ServiceException.class,
                () ->
                        contributionFileService.updateContributionFileStatus(
                                contributionFileStatusDTO));
        verify(contributionFileRepository).getContributionFileStatus(contributionFileStatusDTO);
        verify(contributionFileRepository).approveContributionFile(contributionFileStatusDTO);
    }
}
