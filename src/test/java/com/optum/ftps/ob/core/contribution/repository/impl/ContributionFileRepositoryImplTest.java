package com.optum.ftps.ob.core.contribution.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.optum.ftps.ob.core.contribution.constants.ContributionFileQueryConstants;
import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileNamesDTO;
import com.optum.ftps.ob.core.contribution.mapper.ContributionFileNamesDbToDTOMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ContributionFileRepositoryImplTest {

    @Mock ContributionFileNamesDbToDTOMapper contributionFileNamesDbToDTOMapper;
    @Mock Query query;
    @Mock EntityManager entityManager;

    @InjectMocks ContributionFileRepositoryImpl contributionFileRepositoryImpl;

    private static final ContributionFileNamesDTO mockContributionFileNamesDTO =
            new ContributionFileNamesDTO(null, null, null, null, null, null);

    @Test
    void getContributionFileNames_ok() {
        // Arrange
        var contributionFilePortalIds = List.of("1", "2");
        var queryStringBuilder =
                new StringBuilder()
                        .append(ContributionFileQueryConstants.CONTRIBUTION_FILE_NAMES)
                        .append(ContributionFileQueryConstants.CONTRIBUTION_FILE_CONDITION)
                        .append("?,?")
                        .append(ContributionFileQueryConstants.BRACKET);
        var rows = new ArrayList<Object[]>();
        when(entityManager.createNativeQuery(queryStringBuilder.toString())).thenReturn(query);
        List<ContributionFileNamesDTO> expectedResponse = List.of(mockContributionFileNamesDTO);
        when(contributionFileNamesDbToDTOMapper.mapContributionFileNamesDbToDTO(rows))
                .thenReturn(expectedResponse);
        // Act
        var response =
                contributionFileRepositoryImpl.getContributionFileNames(contributionFilePortalIds);
        // Assert
        assertEquals(expectedResponse, response);
        verify(entityManager).createNativeQuery(queryStringBuilder.toString());
        verify(query).setParameter(1, "1");
        verify(query).setParameter(2, "2");
    }

    @Test
    void getContributionFileNames_empty() {
        // Arrange
        var contributionFilePortalIds = new ArrayList<String>();
        // Act
        var contributionFileNames =
                contributionFileRepositoryImpl.getContributionFileNames(contributionFilePortalIds);
        // Assert
        assertEquals(0, contributionFileNames.size());
    }
}
