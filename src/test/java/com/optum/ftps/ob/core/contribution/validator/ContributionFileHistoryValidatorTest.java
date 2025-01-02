package com.optum.ftps.ob.core.contribution.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.optum.ftps.ob.core.contribution.constants.ErrorCodeConstants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ContributionFileHistoryValidatorTest {

    private ContributionFileHistoryValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ContributionFileHistoryValidator();
    }

    @Test
    void testValidateContributionFileHistory_ValidInput() {
        String customerPolicyNumber = "12345";
        String effectiveTransactionDate = "2023-01-01";

        // Act
        List<Integer> errors =
                validator.validateContributionFileHistory(
                        customerPolicyNumber, effectiveTransactionDate);

        // Assert
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateContributionFileHistory_MissingRequiredFields() {
        // Arrange
        String customerPolicyNumber = "";
        String effectiveTransactionDate = "2023-01-01";

        // Act
        List<Integer> errors =
                validator.validateContributionFileHistory(
                        customerPolicyNumber, effectiveTransactionDate);

        // Assert
        assertEquals(1, errors.size());
        assertEquals(ErrorCodeConstants.REQUIRED_FIELDS_MISSING, errors.get(0).intValue());
    }

    @Test
    void testValidateContributionFileHistory_IncorrectFormat() {
        // Arrange
        String customerPolicyNumber = "12345";
        String effectiveTransactionDate = "2023-01-01-01"; // Incorrect format

        // Act
        List<Integer> errors =
                validator.validateContributionFileHistory(
                        customerPolicyNumber, effectiveTransactionDate);

        // Assert
        assertEquals(1, errors.size());
        assertEquals(ErrorCodeConstants.INCORRECT_FORMAT, errors.get(0).intValue());
    }

    @Test
    void testValidateContributionFileHistory_InvalidFieldLengths() {
        // Arrange
        String customerPolicyNumber = "1234567890123";
        String effectiveTransactionDate = "2023-01-01";

        // Act
        List<Integer> errors =
                validator.validateContributionFileHistory(
                        customerPolicyNumber, effectiveTransactionDate);

        // Assert
        assertEquals(1, errors.size());
        assertEquals(ErrorCodeConstants.INCORRECT_FORMAT, errors.get(0).intValue());
    }
}
