package com.optum.ftps.ob.core.contribution.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.optum.ftps.ob.core.contribution.dtos.contributionFile.ContributionFileItemDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ContributionFileTransactionDbToDTOMapperTest {

    private ContributionFileTransactionDbToDTOMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ContributionFileTransactionDbToDTOMapper();
    }

    @Test
    void testMapToContributionFileTransactionResponseDTO_ValidInput() {
        // Arrange
        List<Object[]> dbData = new ArrayList<>();
        dbData.add(
                new Object[] {
                    "12345",
                    "67890",
                    "Description",
                    "123-45-6789",
                    "100.00",
                    "TypeName",
                    "CommitText"
                });

        // Act
        List<ContributionFileItemDTO> result =
                mapper.mapToContributionFileTransactionResponseDTO(dbData);

        // Assert
        assertEquals(1, result.size());
        ContributionFileItemDTO item = result.getFirst();
        assertEquals("12345", item.contributionFilePortalId());
        assertEquals("67890", item.accountNumber());
        assertEquals("Description", item.transactionDescription());
        assertEquals("123-45-6789", item.ssn());
        assertEquals("100.00", item.crAmt());
        assertEquals("TypeName", item.contributionTypeName());
        assertEquals("CommitText", item.portalContributionCommitText());
    }

    @Test
    void testMapToContributionFileTransactionResponseDTO_EmptyInput() {
        // Arrange
        List<Object[]> dbData = new ArrayList<>();

        // Act
        List<ContributionFileItemDTO> result =
                mapper.mapToContributionFileTransactionResponseDTO(dbData);

        // Assert
        assertTrue(result.isEmpty());
    }
}
