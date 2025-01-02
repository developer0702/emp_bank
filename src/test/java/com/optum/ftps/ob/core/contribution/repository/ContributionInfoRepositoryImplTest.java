package com.optum.ftps.ob.core.contribution.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.optum.ftps.ob.core.contribution.constants.ContributionInfoQueryConstants;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.detail.ContributionDetailsDbDTO;
import com.optum.ftps.ob.core.contribution.dtos.contributioninfo.summary.ContributionSummaryDbDTO;
import com.optum.ftps.ob.core.contribution.repository.impl.ContributionInfoRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ContributionInfoRepositoryImplTest {

    @Mock private EntityManager entityManager;

    @Mock private Query query;

    @InjectMocks @Spy private ContributionInfoRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetValidContributionRecords() {
        // Arrange
        String contributionFileId = "file1";
        List<Object[]> rows = new ArrayList<>();
        rows.add(
                new Object[] {
                    "ach1", "prt1", "ssn1", "first1", "middle1", "last1", 100.0, "type1", "Y",
                    "2023"
                });
        when(entityManager.createNativeQuery(
                        ContributionInfoQueryConstants.GET_VALID_CONTRIBUTION_RECS))
                .thenReturn(query);
        when(query.setParameter("contributionFileId", contributionFileId)).thenReturn(query);
        when(query.getResultList()).thenReturn(rows);

        // Act
        List<ContributionDetailsDbDTO> result =
                repository.getValidContributionRecords(contributionFileId);

        // Assert
        assertEquals(1, result.size());
        assertEquals("ach1", result.get(0).getAchContributionId());
    }

    @Test
    void testGetInvalidContributionRecords() {
        // Arrange
        String contributionFileId = "file1";
        List<Object[]> rows = new ArrayList<>();
        rows.add(
                new Object[] {
                    "ach1",
                    "prt1",
                    "ssn1",
                    "first1",
                    "middle1",
                    "last1",
                    100.0,
                    "type1",
                    "N",
                    "2023",
                    "error1",
                    "errorText1"
                });
        when(entityManager.createNativeQuery(
                        ContributionInfoQueryConstants.GET_INAVLID_CONTRIBUTION_RECS))
                .thenReturn(query);
        when(query.setParameter("contributionFileId", contributionFileId)).thenReturn(query);
        when(query.getResultList()).thenReturn(rows);

        // Act
        List<ContributionDetailsDbDTO> result =
                repository.getInvalidContributionRecords(contributionFileId);

        // Assert
        assertEquals(1, result.size());
        assertEquals("ach1", result.get(0).getAchContributionId());
    }

    @Test
    void testGetAllContributionRecords() {
        // Arrange
        String contributionFileId = "file1";
        List<Object[]> rows = new ArrayList<>();
        rows.add(
                new Object[] {
                    "ach1",
                    "prt1",
                    "ssn1",
                    "first1",
                    "middle1",
                    "last1",
                    100.0,
                    "type1",
                    "N",
                    "2023",
                    "error1",
                    "errorText1"
                });
        when(entityManager.createNativeQuery(
                        ContributionInfoQueryConstants.GET_ALL_CONTRIBUTION_RECS))
                .thenReturn(query);
        when(query.setParameter("contributionFileId", contributionFileId)).thenReturn(query);
        when(query.getResultList()).thenReturn(rows);

        // Act
        List<ContributionDetailsDbDTO> result =
                repository.getAllContributionRecords(contributionFileId);

        // Assert
        assertEquals(1, result.size());
        assertEquals("ach1", result.get(0).getAchContributionId());
    }

    @Test
    void testGetFundYDateRangeMaxFiles() {
        // Arrange
        String employerGroupId = "group1";
        Integer maxNoOfFiles = 10;
        processGetFundYDateRangeMaxFiles(employerGroupId, maxNoOfFiles);
    }

    @Test
    void testGetFundYDateRangeMaxFilesZero() {
        // Arrange
        String employerGroupId = "group1";
        Integer maxNoOfFiles = 0;
        processGetFundYDateRangeMaxFiles(employerGroupId, maxNoOfFiles);
    }

    @Test
    void testGetFundYDateRangeMaxFilesNull() {
        // Arrange
        String employerGroupId = "group1";
        Integer maxNoOfFiles = null;
        processGetFundYDateRangeMaxFiles(employerGroupId, maxNoOfFiles);
    }

    void processGetFundYDateRangeMaxFiles(String employerGroupId, Integer maxNoOfFiles) {
        Date dateFrom = new Date();
        Date dateTo = new Date();
        List<Object[]> rows = new ArrayList<>();
        rows.add(
                new Object[] {
                    "prt1",
                    "policy1",
                    "2023-10-01",
                    "2023-10-02",
                    1,
                    100.0,
                    0,
                    0.0,
                    "code1",
                    "severity1",
                    "text1"
                });

        Mockito.doReturn(query).when(entityManager).createNativeQuery(any());
        Mockito.doReturn(query).when(query).setParameter("employerGroupId", employerGroupId);
        Mockito.doReturn(query)
                .when(query)
                .setParameter("dateFrom", new java.sql.Date(dateFrom.getTime()));
        Mockito.doReturn(query)
                .when(query)
                .setParameter("dateTo", new java.sql.Date(dateTo.getTime()));
        Mockito.doReturn(rows).when(query).getResultList();

        // Act
        List<ContributionSummaryDbDTO> result =
                repository.getFundYDateRangeMaxFiles(
                        employerGroupId, maxNoOfFiles, dateFrom, dateTo);

        // Assert
        assertEquals(1, result.size());
        assertEquals("prt1", result.get(0).getContributionFilePrtlId());
    }

    @Test
    void testGetFundYMaxFiles() {
        // Arrange
        Integer maxNoOfFiles = 10;
        processGetFundYMaxFiles(maxNoOfFiles);
    }

    @Test
    void testGetFundYMaxFilesZero() {
        processGetFundYMaxFiles(0);
    }

    @Test
    void testGetFundYMaxFilesNull() {
        processGetFundYMaxFiles(null);
    }

    void processGetFundYMaxFiles(Integer maxNoOfFiles) {
        String employerGroupId = "group1";
        Date dateTo = new Date();
        List<Object[]> rows = new ArrayList<>();
        rows.add(
                new Object[] {
                    "prt1",
                    "policy1",
                    "2023-10-01",
                    "2023-10-02",
                    1,
                    100.0,
                    0,
                    0.0,
                    "code1",
                    "severity1",
                    "text1"
                });
        when(entityManager.createNativeQuery(any())).thenReturn(query);
        when(query.setParameter("employerGroupId", employerGroupId)).thenReturn(query);
        when(query.setParameter("dateFrom", new java.sql.Date(dateTo.getTime()))).thenReturn(query);
        when(query.getResultList()).thenReturn(rows);

        // Act
        List<ContributionSummaryDbDTO> result =
                repository.getFundYMaxFiles(employerGroupId, maxNoOfFiles, dateTo);

        // Assert
        assertEquals(1, result.size());
        assertEquals("prt1", result.get(0).getContributionFilePrtlId());
    }

    @Test
    void testGetFundNDateRangeMaxFiles() {
        // Arrange
        Integer maxNoOfFiles = 10;
        processGetFundNDateRangeMaxFiles(maxNoOfFiles);
    }

    @Test
    void testGetFundNDateRangeMaxFilesZero() {
        processGetFundNDateRangeMaxFiles(0);
    }

    @Test
    void testGetFundNDateRangeMaxFilesNull() {
        processGetFundNDateRangeMaxFiles(null);
    }

    void processGetFundNDateRangeMaxFiles(Integer maxNoOfFiles) {
        String employerGroupId = "group1";
        Date dateFrom = new Date();
        Date dateTo = new Date();
        List<Object[]> rows = new ArrayList<>();
        rows.add(
                new Object[] {
                    "prt1",
                    "policy1",
                    "2023-10-01",
                    "2023-10-02",
                    1,
                    100.0,
                    0,
                    0.0,
                    "code1",
                    "severity1",
                    "text1"
                });
        when(entityManager.createNativeQuery(any())).thenReturn(query);
        when(query.setParameter("employerGroupId", employerGroupId)).thenReturn(query);
        when(query.setParameter("dateFrom", new java.sql.Date(dateFrom.getTime())))
                .thenReturn(query);
        when(query.setParameter("dateTo", new java.sql.Date(dateTo.getTime()))).thenReturn(query);
        when(query.getResultList()).thenReturn(rows);

        // Act
        List<ContributionSummaryDbDTO> result =
                repository.getFundNDateRangeMaxFiles(
                        employerGroupId, maxNoOfFiles, dateFrom, dateTo);

        // Assert
        assertEquals(1, result.size());
        assertEquals("prt1", result.get(0).getContributionFilePrtlId());
    }

    @Test
    void testGetFundNMaxFiles() {
        Integer maxNoOfFiles = 10;
        processGetFundNMaxFiles(maxNoOfFiles);
    }

    @Test
    void testGetFundNMaxFilesZero() {
        processGetFundNMaxFiles(0);
    }

    @Test
    void testGetFundNMaxFilesNull() {
        processGetFundNMaxFiles(null);
    }

    void processGetFundNMaxFiles(Integer maxNoOfFiles) {
        String employerGroupId = "group1";
        List<Object[]> rows = new ArrayList<>();
        rows.add(
                new Object[] {
                    "prt1",
                    "policy1",
                    "2023-10-01",
                    "2023-10-02",
                    1,
                    100.0,
                    0,
                    0.0,
                    "code1",
                    "severity1",
                    "text1"
                });
        when(entityManager.createNativeQuery(any())).thenReturn(query);
        when(query.setParameter("employerGroupId", employerGroupId)).thenReturn(query);
        when(query.getResultList()).thenReturn(rows);

        // Act
        List<ContributionSummaryDbDTO> result =
                repository.getFundNMaxFiles(employerGroupId, maxNoOfFiles);

        // Assert
        assertEquals(1, result.size());
        assertEquals("prt1", result.get(0).getContributionFilePrtlId());
    }
}
