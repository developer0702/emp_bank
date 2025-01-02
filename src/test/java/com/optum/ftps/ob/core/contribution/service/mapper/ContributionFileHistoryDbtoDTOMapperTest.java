package com.optum.ftps.ob.core.contribution.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileHistoryRecordsDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ContributionFileHistoryDbtoDTOMapperTest {

    private ContributionFileHistoryDbtoDTOMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ContributionFileHistoryDbtoDTOMapper();
    }

    @Test
    void testMapToContributionFileHistoryResponseDTO_ValidInput() {
        // Arrange
        List<Object[]> dbData = new ArrayList<>();
        dbData.add(
                new Object[] {
                    "1",
                    "2023-01-01",
                    "200",
                    "ONLINE",
                    "1000",
                    "original.txt",
                    "new.txt",
                    "friendly.txt",
                    "John Doe",
                    "2023-01-01 10:00:00",
                    "Jane Doe",
                    "2023-01-01 11:00:00",
                    "Jim Doe",
                    "2023-01-01 12:00:00",
                    "No comments",
                    "2023-01-01 13:00:00",
                    "12345",
                    "2023-01-01 14:00:00",
                    "67890",
                    "Bank A",
                    "Bank Nickname"
                });

        // Act
        List<ContributionFileHistoryRecordsDTO> result =
                mapper.mapToContributionFileHistoryResponseDTO(dbData);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testMapToContributionFileHistoryResponseDTO_NullInput() {
        // Arrange
        List<Object[]> dbData = null;

        // Act
        List<ContributionFileHistoryRecordsDTO> result =
                mapper.mapToContributionFileHistoryResponseDTO(dbData);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testMapToContributionFileHistoryResponseDTO_EmptyInput() {
        // Arrange
        List<Object[]> dbData = new ArrayList<>();

        // Act
        List<ContributionFileHistoryRecordsDTO> result =
                mapper.mapToContributionFileHistoryResponseDTO(dbData);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
