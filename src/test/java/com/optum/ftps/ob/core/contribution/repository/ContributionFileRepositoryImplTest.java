package com.optum.ftps.ob.core.contribution.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileHistoryRecordsDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileItemDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileStatusDbDTO;
import com.optum.ftps.ob.core.contribution.repository.impl.ContributionFileRepositoryImpl;
import com.optum.ftps.ob.core.contribution.service.mapper.ContributionFileHistoryDbtoDTOMapper;
import com.optum.ftps.ob.core.contribution.service.mapper.ContributionFileStatusDbtoDTOMapper;
import com.optum.ftps.ob.core.contribution.service.mapper.ContributionFileTransactionDbToDTOMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ContributionFileRepositoryImplTest {

    @Mock private EntityManager entityManager;

    @Mock private Query query;

    @Mock private ContributionFileHistoryDbtoDTOMapper mapper;

    @Mock private ContributionFileTransactionDbToDTOMapper contributionFileTransactiondbToDTOMapper;

    @Mock private ContributionFileStatusDbtoDTOMapper contributionFileStatusDbtoDTOMapper;
    @InjectMocks @Spy private ContributionFileRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        when(entityManager.createNativeQuery(anyString())).thenReturn(query);
    }

    @Test
    void testGetEmployerContributionFileHistory_ValidInput() {
        // Arrange
        String customerPolicyNumber = "12345";
        String effectiveTransactionDate = "2023-01-01";

        doReturn(Collections.emptyList())
                .when(mapper)
                .mapToContributionFileHistoryResponseDTO(Collections.emptyList());
        // Act
        List<ContributionFileHistoryRecordsDTO> result =
                repository.getEmployerContributionFileHistory(
                        customerPolicyNumber, effectiveTransactionDate);

        // Assert
        verify(query).setParameter("customerPolicyNumber", "12345");
        verify(query).setParameter("effectiveTransactionDate", "2023-01-01");
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetEmployerContributionFileHistory_NullInput() {
        // Arrange
        String customerPolicyNumber = null;
        String effectiveTransactionDate = null;

        doReturn(Collections.emptyList())
                .when(mapper)
                .mapToContributionFileHistoryResponseDTO(Collections.emptyList());

        // Act
        List<ContributionFileHistoryRecordsDTO> result =
                repository.getEmployerContributionFileHistory(
                        customerPolicyNumber, effectiveTransactionDate);

        // Assert
        verify(query).setParameter("customerPolicyNumber", null);
        verify(query).setParameter("effectiveTransactionDate", null);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetEmployerContributionFileTransactions_ValidInput() {
        // Arrange
        String contributionFilePortalId = "12345";
        when(contributionFileTransactiondbToDTOMapper.mapToContributionFileTransactionResponseDTO(
                        Collections.emptyList()))
                .thenReturn(Collections.emptyList());

        // Act
        List<ContributionFileItemDTO> result =
                repository.getEmployerContributionFileTransactions(contributionFilePortalId);

        // Assert
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetEmployerContributionFileTransactions_EmptyData() {
        // Arrange
        String contributionFilePortalId = "12345";

        // Act
        List<ContributionFileItemDTO> result =
                repository.getEmployerContributionFileTransactions(contributionFilePortalId);

        // Assert
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetContributionFileStatus_ValidInput() {
        // Arrange
        ContributionFileStatusDTO contributionFileStatusDTO =
                new ContributionFileStatusDTO(12345, null, null, null, null, null);

        doReturn(Collections.emptyList())
                .when(contributionFileStatusDbtoDTOMapper)
                .map(Collections.emptyList());

        // Act
        List<ContributionFileStatusDbDTO> result =
                repository.getContributionFileStatus(contributionFileStatusDTO);

        // Assert
        verify(query).setParameter("contributionFileId", 12345);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetContributionFileStatus_NullInput() {
        // Arrange
        ContributionFileStatusDTO contributionFileStatusDTO =
                new ContributionFileStatusDTO(null, null, null, null, null, null);

        doReturn(Collections.emptyList())
                .when(contributionFileStatusDbtoDTOMapper)
                .map(Collections.emptyList());

        // Act
        List<ContributionFileStatusDbDTO> result =
                repository.getContributionFileStatus(contributionFileStatusDTO);

        // Assert
        verify(query).setParameter("contributionFileId", null);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testIsValidFundId_ValidInput() {
        // Arrange
        ContributionFileStatusDTO contributionFileStatusDTO =
                new ContributionFileStatusDTO(12345, 67890, null, null, null, null);
        doReturn(Collections.singletonList("valid")).when(query).getResultList();

        // Act
        boolean result = repository.isValidFundId(contributionFileStatusDTO);

        // Assert
        verify(query).setParameter("fundId", 67890);
        verify(query).setParameter("contributionFileId", 12345);
        assertTrue(result);
    }

    @Test
    void testIsValidFundId_InvalidInput() {
        // Arrange
        ContributionFileStatusDTO contributionFileStatusDTO =
                new ContributionFileStatusDTO(12345, 67890, null, null, null, null);

        doReturn(Collections.emptyList()).when(query).getResultList();

        // Act
        boolean result = repository.isValidFundId(contributionFileStatusDTO);

        // Assert
        verify(query).setParameter("fundId", 67890);
        verify(query).setParameter("contributionFileId", 12345);
        assertFalse(result);
    }

    @Test
    void testIsValidFundId_NullInput() {
        // Arrange
        ContributionFileStatusDTO contributionFileStatusDTO =
                new ContributionFileStatusDTO(null, null, null, null, null, null);

        doReturn(Collections.emptyList()).when(query).getResultList();

        // Act
        boolean result = repository.isValidFundId(contributionFileStatusDTO);

        // Assert
        verify(query).setParameter("fundId", null);
        verify(query).setParameter("contributionFileId", null);
        assertFalse(result);
    }

    @Test
    void testApproveContributionFile_ValidInput() {
        // Arrange
        ContributionFileStatusDTO contributionFileStatusDTO =
                new ContributionFileStatusDTO(12345, 67890, null, "user", null, "APPROVED");

        when(query.executeUpdate()).thenReturn(1);

        // Act
        int result = repository.approveContributionFile(contributionFileStatusDTO);

        // Assert
        verify(query).setParameter("statusCode", "APPROVED");
        verify(query).setParameter("fundId", 67890);
        verify(query).setParameter("userName", "user");
        verify(query).setParameter("contributionFileId", 12345);
        assertEquals(1, result);
    }

    @Test
    void testApproveContributionFile_InvalidInput() {
        // Arrange
        ContributionFileStatusDTO contributionFileStatusDTO =
                new ContributionFileStatusDTO(null, null, null, null, null, null);

        when(query.executeUpdate()).thenReturn(0);

        // Act
        int result = repository.approveContributionFile(contributionFileStatusDTO);

        // Assert
        verify(query).setParameter("statusCode", null);
        verify(query).setParameter("fundId", null);
        verify(query).setParameter("userName", null);
        verify(query).setParameter("effTransDate", null);
        verify(query).setParameter("contributionFileId", null);
        assertEquals(0, result);
    }

    @Test
    void testDenyContributionFile_ValidInput() {
        // Arrange
        ContributionFileStatusDTO contributionFileStatusDTO =
                new ContributionFileStatusDTO(12345, null, null, "user", "Invalid data", "DENIED");

        when(query.executeUpdate()).thenReturn(1);

        // Act
        int result = repository.denyContributionFile(contributionFileStatusDTO);

        // Assert
        verify(query).setParameter("statusCode", "DENIED");
        verify(query).setParameter("userName", "user");
        verify(query).setParameter("comments", "Invalid data");
        verify(query).setParameter("contributionFileId", 12345);
        assertEquals(1, result);
    }

    @Test
    void testDenyContributionFile_InvalidInput() {
        // Arrange
        ContributionFileStatusDTO contributionFileStatusDTO =
                new ContributionFileStatusDTO(null, null, null, null, null, null);

        when(query.executeUpdate()).thenReturn(0);

        // Act
        int result = repository.denyContributionFile(contributionFileStatusDTO);

        // Assert
        verify(query).setParameter("statusCode", null);
        verify(query).setParameter("userName", null);
        verify(query).setParameter("comments", null);
        verify(query).setParameter("contributionFileId", null);
        assertEquals(0, result);
    }
}
