package com.optum.ftps.ob.core.contribution.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.optum.ftps.ob.core.contribution.constants.ErrorCodeConstants;
import com.optum.ftps.ob.core.contribution.constants.FileStatusCode;
import com.optum.ftps.ob.core.contribution.model.v1.UpdateContributionFileStatusRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Set;

class ContributionFileStatusValidatorTest {

    private ContributionFileStatusValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ContributionFileStatusValidator();
    }

    @Test
    void testValidateContributionStatus_AllValid() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setContributionFileId("123");
        request.setChangedByName("John Doe");
        request.setFileStatusCode(FileStatusCode.APPROVED.getStatusCode());
        request.setCustomerFundId("456");
        request.setEffectiveTransactionDate("2023-01-01");

        // Act
        Set<Integer> errors = validator.validateContributionStatus(request);

        // Assert
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateContributionStatus_MissingRequiredFields() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setChangedByName("John Doe");
        request.setFileStatusCode(FileStatusCode.APPROVED.getStatusCode());

        // Act
        Set<Integer> errors = validator.validateContributionStatus(request);

        // Assert
        assertTrue(errors.contains(ErrorCodeConstants.REQUIRED_FIELDS_MISSING));
    }

    @Test
    void testValidateContributionStatus_InvalidContributionFileId() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setContributionFileId("abc"); // Invalid ID
        request.setChangedByName("John Doe");
        request.setFileStatusCode(FileStatusCode.APPROVED.getStatusCode());
        request.setCustomerFundId("456");
        request.setEffectiveTransactionDate("2023-01-01");

        // Act
        Set<Integer> errors = validator.validateContributionStatus(request);

        // Assert
        assertTrue(errors.contains(ErrorCodeConstants.RECORD_NOT_FOUND_ERROR_CODE));
    }

    @Test
    void testValidateContributionStatus_InvalidFileStatusCode() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setContributionFileId("123");
        request.setChangedByName("John Doe");
        request.setFileStatusCode("INVALID"); // Invalid status code

        // Act
        Set<Integer> errors = validator.validateContributionStatus(request);

        // Assert
        assertTrue(errors.contains(ErrorCodeConstants.INCORRECT_FORMAT));
    }

    @Test
    void testValidateContributionStatus_InvalidFieldsFormat() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setContributionFileId("123");
        request.setChangedByName("John Doe");
        request.setFileStatusCode(FileStatusCode.APPROVED.getStatusCode());
        request.setCustomerFundId("abc"); // Invalid fund ID
        request.setEffectiveTransactionDate("2023-01-01");

        // Act
        Set<Integer> errors = validator.validateContributionStatus(request);

        // Assert
        assertTrue(errors.contains(ErrorCodeConstants.INCORRECT_FORMAT));
    }

    @Test
    void testCheckContributionFileIdValid_ValidId() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setContributionFileId("123");

        // Act
        boolean result = validator.checkContributionFileIdValid(request);

        // Assert
        assertTrue(result);
    }

    @Test
    void testCheckContributionFileIdValid_InvalidId() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setContributionFileId("abc");

        // Act
        boolean result = validator.checkContributionFileIdValid(request);

        // Assert
        assertFalse(result);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testCheckContributionFileIdValid_NullId(String id) {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setContributionFileId(id);

        // Act
        boolean result = validator.checkContributionFileIdValid(request);

        // Assert
        assertFalse(result);
    }

    @Test
    void testCheckRequiredFieldsPresent_AllFieldsPresent() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setContributionFileId("123");
        request.setChangedByName("John Doe");
        request.setFileStatusCode("A");

        // Act
        boolean result = validator.checkRequiredFieldsPresent(request);

        // Assert
        assertTrue(result);
    }

    @Test
    void testCheckRequiredFieldsPresent_MissingContributionFileId() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setChangedByName("John Doe");
        request.setFileStatusCode("A");

        // Act
        boolean result = validator.checkRequiredFieldsPresent(request);

        // Assert
        assertFalse(result);
    }

    @Test
    void testCheckRequiredFieldsPresent_MissingChangedByName() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setContributionFileId("123");
        request.setFileStatusCode("A");

        // Act
        boolean result = validator.checkRequiredFieldsPresent(request);

        // Assert
        assertFalse(result);
    }

    @Test
    void testCheckRequiredFieldsPresent_MissingFileStatusCode() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setContributionFileId("123");
        request.setChangedByName("John Doe");

        // Act
        boolean result = validator.checkRequiredFieldsPresent(request);

        // Assert
        assertFalse(result);
    }

    @Test
    void testCheckRequiredFieldsPresent_AllFieldsMissing() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();

        // Act
        boolean result = validator.checkRequiredFieldsPresent(request);

        // Assert
        assertFalse(result);
    }

    @Test
    void testCheckFieldsFormat_ValidApproved() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setContributionFileId("123");
        request.setCustomerFundId("456");
        request.setFileStatusCode(FileStatusCode.APPROVED.getStatusCode());

        // Act
        boolean result = validator.checkFieldsFormat(request, FileStatusCode.APPROVED);

        // Assert
        assertTrue(result);
    }

    @Test
    void testCheckFieldsFormat_InvalidApproved() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setContributionFileId("123");
        request.setCustomerFundId("abc"); // Invalid fund ID
        request.setFileStatusCode(FileStatusCode.APPROVED.getStatusCode());

        // Act
        boolean result = validator.checkFieldsFormat(request, FileStatusCode.APPROVED);

        // Assert
        assertFalse(result);
    }

    @Test
    void testCheckFieldsFormat_ValidOtherStatus() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setContributionFileId("123");
        request.setFileStatusCode(FileStatusCode.DENIED.getStatusCode());

        // Act
        boolean result = validator.checkFieldsFormat(request, FileStatusCode.DENIED);

        // Assert
        assertTrue(result);
    }

    @Test
    void testCheckFieldsFormat_InvalidOtherStatus() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setContributionFileId("abc"); // Invalid file ID
        request.setFileStatusCode(FileStatusCode.DENIED.getStatusCode());

        // Act
        boolean result = validator.checkFieldsFormat(request, FileStatusCode.DENIED);

        // Assert
        assertFalse(result);
    }

    @Test
    void testSanitizeRequest() {
        // Arrange
        UpdateContributionFileStatusRequest request = new UpdateContributionFileStatusRequest();
        request.setContributionFileId(" 123 ");
        request.setChangedByName(" John Doe ");
        request.setFileStatusCode(" A ");
        request.setCustomerFundId(" 456 ");
        request.setCommentText(" Comment ");
        request.setEffectiveTransactionDate(" 2023-01-01 ");

        // Act
        validator.sanitizeRequest(request);

        // Assert
        assertEquals("123", request.getContributionFileId());
        assertEquals("John Doe", request.getChangedByName());
        assertEquals("A", request.getFileStatusCode());
        assertEquals("456", request.getCustomerFundId());
        assertEquals("Comment", request.getCommentText());
        assertEquals("2023-01-01", request.getEffectiveTransactionDate());
    }
}
