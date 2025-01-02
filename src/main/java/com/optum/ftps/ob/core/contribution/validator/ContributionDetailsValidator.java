package com.optum.ftps.ob.core.contribution.validator;

import com.optum.ftps.ob.core.contribution.constants.ContributionInfoConstants;
import com.optum.ftps.ob.core.contribution.constants.ErrorCodeConstants;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionDetailsRequest;
import com.optum.ftps.ob.core.contribution.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Component
public class ContributionDetailsValidator {

    private static final String NUM_REGEX = "[0-9]*";
    private static final Pattern NUM_PATTERN = Pattern.compile(NUM_REGEX);
    private static final String STR_REGEX = "[AVI]";
    private static final Pattern STR_PATTERN = Pattern.compile(STR_REGEX);

    public List<Integer> validateContributionDetails(ContributionDetailsRequest request) {

        sanitizeRequest(request);
        List<Integer> errors = new ArrayList<>();

        if (!checkRequiredFieldsPresent(request)) {
            log.error("Inside validation Exception as required fields not present");
            errors.add(ErrorCodeConstants.REQUIRED_FIELDS_MISSING);
        }

        // check whether all the fields are in correct format.
        if (!checkFieldsFormat(request)) {
            log.error("Inside validation Exception for invalid format");
            errors.add(ErrorCodeConstants.INCORRECT_FORMAT);
        }

        var fieldLengthValid = checkFieldLengths(request);
        var reqId = StringUtil.isEmpty(request.getRequestId()) ? "" : request.getRequestId();
        if (!fieldLengthValid || reqId.length() > ContributionInfoConstants.REQUEST_ID_LENGTH) {
            log.error("Inside validation Exception for invalid format");
            errors.add(ErrorCodeConstants.INCORRECT_FORMAT);
        }
        return errors;
    }

    static void sanitizeRequest(ContributionDetailsRequest request) {
        request.setRequestId(StringUtil.sanitize(request.getRequestId()));
        var fileDetails = request.getFileDetails();
        if (CollectionUtils.isEmpty(fileDetails)) {
            return;
        }
        var details =
                fileDetails.stream()
                        .map(
                                fileDetail -> {
                                    fileDetail.setContributionFileId(
                                            StringUtil.sanitize(
                                                    fileDetail.getContributionFileId()));
                                    fileDetail.setRequestType(
                                            StringUtil.sanitize(fileDetail.getRequestType()));
                                    return fileDetail;
                                })
                        .toList();
        request.setFileDetails(details);
    }

    static boolean checkFieldLengths(ContributionDetailsRequest request) {
        var fileDetails = request.getFileDetails();
        return !fileDetails.isEmpty()
                && fileDetails.stream()
                        .allMatch(
                                detail ->
                                        detail.getContributionFileId().length()
                                                <= ContributionInfoConstants.ACH_FILEID_LENGTH);
    }

    static boolean checkRequiredFieldsPresent(ContributionDetailsRequest request) {
        var fileDetails = request.getFileDetails();
        return !CollectionUtils.isEmpty(fileDetails)
                && fileDetails.stream()
                        .allMatch(
                                fileDetail ->
                                        !StringUtil.isEmpty(fileDetail.getContributionFileId())
                                                && !StringUtil.isEmpty(
                                                        fileDetail.getRequestType()));
    }

    static boolean checkFieldsFormat(ContributionDetailsRequest request) {
        var fileDetails = request.getFileDetails();

        return !fileDetails.isEmpty()
                && fileDetails.stream()
                        .allMatch(
                                detail -> {
                                    var isContributionFileIDValid =
                                            detail.getContributionFileId() == null
                                                    || NUM_PATTERN
                                                            .matcher(detail.getContributionFileId())
                                                            .matches();
                                    var isRequestTypeValid =
                                            detail.getRequestType() == null
                                                    || STR_PATTERN
                                                            .matcher(detail.getRequestType())
                                                            .matches();
                                    return isContributionFileIDValid && isRequestTypeValid;
                                });
    }
}
