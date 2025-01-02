package com.optum.ftps.ob.core.contribution.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileNamesDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ContributionFileNamesDbToDTOMapperTest {
    @InjectMocks ContributionFileNamesDbToDTOMapper contributionFileNamesDbToDTOMapper;

    @Test
    void mapContributionFileNamesDbToDTO_ok() {
        // Arrange
        List<Object[]> dbResponse =
                List.of(
                        new Object[] {"1", "2", "3", "4", "5", "6"},
                        new Object[] {"7", "8", "9", "10", "11", "12"});
        var expectedResult =
                List.of(
                        new ContributionFileNamesDTO("1", "2", "3", "4", "5", "6"),
                        new ContributionFileNamesDTO("7", "8", "9", "10", "11", "12"));
        // Act
        var result = contributionFileNamesDbToDTOMapper.mapContributionFileNamesDbToDTO(dbResponse);
        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void mapContributionFileNamesDbToDTO_empty() {
        // Arrange
        List<Object[]> dbResponse = List.of();
        // Act
        var result = contributionFileNamesDbToDTOMapper.mapContributionFileNamesDbToDTO(dbResponse);
        // Assert
        assertEquals(0, result.size());
    }
}
