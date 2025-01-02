package com.optum.ftps.ob.core.contribution.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.optum.ftps.ob.core.contribution.constants.ErrorCodeConstants;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionSummaryRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ContributionSummaryValidatorTest {

    private ContributionSummaryValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ContributionSummaryValidator();
    }

    @Test
    void testValidateContributionSummary_ValidInput() {
        // Arrange
        ContributionSummaryRequest request = new ContributionSummaryRequest();
        request.setEmployerGroupId("group1");
        request.setRestrictToFunded("Y");
        request.setDateFrom("2023-01-01");
        request.setDateTo("2023-12-31");
        request.setMaxNumberOfFiles(10);
        request.setRequestId("12345");

        // Act
        List<Integer> errors = validator.validateContributionSummary(request);

        // Assert
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateContributionSummary_MissingRequiredFields() {
        // Arrange
        ContributionSummaryRequest request = new ContributionSummaryRequest();
        request.setEmployerGroupId("");
        request.setRestrictToFunded("Y");
        request.setDateFrom("");
        request.setDateTo("");
        request.setMaxNumberOfFiles(null);
        request.setRequestId("12345");

        // Act
        List<Integer> errors = validator.validateContributionSummary(request);

        // Assert
        assertEquals(1, errors.size());
        assertEquals(ErrorCodeConstants.REQUIRED_FIELDS_MISSING, errors.get(0).intValue());
    }

    @Test
    void testValidateContributionSummary_IncorrectFormat() {
        // Arrange
        ContributionSummaryRequest request = new ContributionSummaryRequest();
        request.setEmployerGroupId("group1!");
        request.setRestrictToFunded("X");
        request.setDateFrom("2023-01-01");
        request.setDateTo("2022-12-31");
        request.setMaxNumberOfFiles(-1);
        request.setRequestId("12345");

        // Act
        List<Integer> errors = validator.validateContributionSummary(request);

        // Assert
        assertEquals(1, errors.size());
        assertEquals(ErrorCodeConstants.INCORRECT_FORMAT, errors.get(0).intValue());
    }

    @Test
    void testValidateContributionSummary_InvalidFieldLengths() {
        // Arrange
        ContributionSummaryRequest request = new ContributionSummaryRequest();
        request.setEmployerGroupId("group1");
        request.setRestrictToFunded("Y");
        request.setDateFrom("2023-01-01");
        request.setDateTo("2023-12-31");
        request.setMaxNumberOfFiles(10);
        request.setRequestId("1234567890123456789012345678901234567890"); // Exceeds max length

        // Act
        List<Integer> errors = validator.validateContributionSummary(request);

        // Assert
        assertEquals(1, errors.size());
        assertEquals(ErrorCodeConstants.INCORRECT_FORMAT, errors.get(0).intValue());
    }
}
