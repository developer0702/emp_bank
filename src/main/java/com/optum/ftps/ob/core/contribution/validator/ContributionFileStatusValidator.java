package com.optum.ftps.ob.core.contribution.validator;

import static com.optum.ftps.ob.core.contribution.util.NumberUtil.parseInt;
import static com.optum.ftps.ob.core.contribution.util.StringUtil.isEmpty;
import static com.optum.ftps.ob.core.contribution.util.StringUtil.sanitize;

import com.optum.ftps.ob.core.contribution.constants.ContributionFileConstants;
import com.optum.ftps.ob.core.contribution.constants.ErrorCodeConstants;
import com.optum.ftps.ob.core.contribution.constants.FileStatusCode;
import com.optum.ftps.ob.core.contribution.model.v1.UpdateContributionFileStatusRequest;
import com.optum.ftps.ob.core.contribution.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.TreeSet;

@Slf4j
@Component
public class ContributionFileStatusValidator {

    public Set<Integer> validateContributionStatus(UpdateContributionFileStatusRequest request) {

        sanitizeRequest(request);
        Set<Integer> errors = new TreeSet<>();

        if (!checkRequiredFieldsPresent(request)) {
            log.error("Inside validation Exception as required fields not present");
            errors.add(ErrorCodeConstants.REQUIRED_FIELDS_MISSING);
        }
        if (!checkContributionFileIdValid(request)) {
            log.error("Inside validation Exception for invalid contribution file id");
            errors.add(ErrorCodeConstants.RECORD_NOT_FOUND_ERROR_CODE);
        }

        var fileStatusCode = FileStatusCode.byCode(request.getFileStatusCode());

        if (null == fileStatusCode) {
            log.error("Inside validation Exception for invalid file status code");
            errors.add(ErrorCodeConstants.INCORRECT_FORMAT);
        } else {
            request.setFileStatusCode(fileStatusCode.getStatusCode());
            if (!checkRequiredFieldsPresent(request, fileStatusCode)) {
                log.error("Inside validation Exception as required fields not present");
                errors.add(ErrorCodeConstants.REQUIRED_FIELDS_MISSING);
            }
            if (!checkFieldsFormat(request, fileStatusCode)) {
                log.error("Inside validation Exception for invalid format");
                errors.add(ErrorCodeConstants.INCORRECT_FORMAT);
            }
        }

        return errors;
    }

    boolean checkRequiredFieldsPresent(UpdateContributionFileStatusRequest request) {
        return !isEmpty(request.getContributionFileId())
                && !isEmpty(request.getChangedByName())
                && !isEmpty(request.getFileStatusCode());
    }

    boolean checkContributionFileIdValid(UpdateContributionFileStatusRequest request) {
        var value = parseInt(request.getContributionFileId(), null);
        return value != null;
    }

    boolean checkRequiredFieldsPresent(
            UpdateContributionFileStatusRequest request, FileStatusCode fileStatusCode) {

        if (FileStatusCode.APPROVED.equals(fileStatusCode)) {
            var date =
                    DateUtil.parse(
                            request.getEffectiveTransactionDate(),
                            ContributionFileConstants.DATE_FORMAT);
            if (null == date) {
                return false;
            }
            return !isEmpty(request.getCustomerFundId());
        }

        return !isEmpty(request.getCommentText());
    }

    boolean checkFieldsFormat(
            UpdateContributionFileStatusRequest request, FileStatusCode fileStatusCode) {
        if (FileStatusCode.APPROVED.equals(fileStatusCode)) {
            var fundId = parseInt(request.getCustomerFundId(), null);
            if (null == fundId) {
                return false;
            }
        }
        var fileId = parseInt(request.getContributionFileId(), null);
        return fileId != null;
    }

    void sanitizeRequest(UpdateContributionFileStatusRequest request) {
        request.setContributionFileId(sanitize(request.getContributionFileId()));
        request.setChangedByName(sanitize(request.getChangedByName()));
        request.setFileStatusCode(sanitize(request.getFileStatusCode()));
        request.setCustomerFundId(sanitize(request.getCustomerFundId()));
        request.setCommentText(sanitize(request.getCommentText()));
        request.setEffectiveTransactionDate(sanitize(request.getEffectiveTransactionDate()));
    }
}
