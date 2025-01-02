package com.optum.ftps.ob.core.contribution.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.optum.ftps.ob.core.contribution.constants.ErrorCodeConstants;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionDetailsRequest;
import com.optum.ftps.ob.core.contribution.model.v1.FileDetails;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class ContributionDetailsValidatorTest {

    private ContributionDetailsValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ContributionDetailsValidator();
    }

    @Test
    void testValidateContributionDetails_ValidInput() {
        // Arrange
        ContributionDetailsRequest request = new ContributionDetailsRequest();
        request.setRequestId("12345");
        FileDetails fileDetail = new FileDetails();
        fileDetail.setContributionFileId("123");
        fileDetail.setRequestType("A");
        request.setFileDetails(Collections.singletonList(fileDetail));

        // Act
        List<Integer> errors = validator.validateContributionDetails(request);

        // Assert
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateContributionDetails_MissingRequiredFields() {
        // Arrange
        ContributionDetailsRequest request = new ContributionDetailsRequest();
        request.setRequestId("12345");
        FileDetails fileDetail = new FileDetails();
        fileDetail.setContributionFileId("");
        fileDetail.setRequestType("V");
        request.setFileDetails(Collections.singletonList(fileDetail));

        // Act
        List<Integer> errors = validator.validateContributionDetails(request);

        // Assert
        assertEquals(1, errors.size());
        assertEquals(ErrorCodeConstants.REQUIRED_FIELDS_MISSING, errors.get(0).intValue());
    }

    @Test
    void testValidateContributionDetails_IncorrectFormat() {
        // Arrange
        ContributionDetailsRequest request = new ContributionDetailsRequest();
        request.setRequestId("12345");
        FileDetails fileDetail = new FileDetails();
        fileDetail.setContributionFileId("abc");
        fileDetail.setRequestType("X");
        request.setFileDetails(Collections.singletonList(fileDetail));

        // Act
        List<Integer> errors = validator.validateContributionDetails(request);

        // Assert
        assertEquals(1, errors.size());
        assertEquals(ErrorCodeConstants.INCORRECT_FORMAT, errors.get(0).intValue());
    }

    @Test
    void testValidateContributionDetails_InvalidFieldLengths() {
        // Arrange
        ContributionDetailsRequest request = new ContributionDetailsRequest();
        request.setRequestId("1234567890123456789012345678901234567890"); // Exceeds max length
        FileDetails fileDetail = new FileDetails();
        fileDetail.setContributionFileId(
                "1234567890123456789012345678901234567890"); // Exceeds max length
        fileDetail.setRequestType("A");
        request.setFileDetails(Collections.singletonList(fileDetail));

        // Act
        List<Integer> errors = validator.validateContributionDetails(request);

        // Assert
        assertEquals(1, errors.size());
        assertEquals(ErrorCodeConstants.INCORRECT_FORMAT, errors.get(0).intValue());
    }
}
