package com.optum.ftps.ob.core.contribution.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.optum.ftps.ob.core.contribution.constants.ErrorCodeConstants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ContributionFileTransactionValidatorTest {

    private ContributionFileTransactionValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ContributionFileTransactionValidator();
    }

    @Test
    void testValidateContributionFileTransaction_ValidInput() {
        // Arrange
        String contributionFilePortalId = "12345";

        // Act
        List<Integer> errors =
                validator.validateContributionFileTransaction(contributionFilePortalId);

        // Assert
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateContributionFileTransaction_MissingRequiredFields() {
        // Arrange
        String contributionFilePortalId = "";

        // Act
        List<Integer> errors =
                validator.validateContributionFileTransaction(contributionFilePortalId);

        // Assert
        assertEquals(2, errors.size());
        assertEquals(ErrorCodeConstants.REQUIRED_FIELDS_MISSING, errors.get(0).intValue());
    }

    @Test
    void testValidateContributionFileTransaction_IncorrectFormat() {
        // Arrange
        String contributionFilePortalId = "123a45"; // Incorrect format

        // Act
        List<Integer> errors =
                validator.validateContributionFileTransaction(contributionFilePortalId);

        // Assert
        assertEquals(1, errors.size());
        assertEquals(ErrorCodeConstants.INCORRECT_FORMAT, errors.get(0).intValue());
    }
}
