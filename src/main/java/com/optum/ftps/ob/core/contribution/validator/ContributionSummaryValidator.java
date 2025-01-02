package com.optum.ftps.ob.core.contribution.validator;

import com.optum.ftps.ob.core.contribution.constants.ContributionInfoConstants;
import com.optum.ftps.ob.core.contribution.constants.ErrorCodeConstants;
import com.optum.ftps.ob.core.contribution.model.v1.ContributionSummaryRequest;
import com.optum.ftps.ob.core.contribution.util.DateUtil;
import com.optum.ftps.ob.core.contribution.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ContributionSummaryValidator {

    private static final String YES = "Y";
    private static final String NO = "N";

    public List<Integer> validateContributionSummary(ContributionSummaryRequest request) {

        sanitizeRequest(request);
        List<Integer> errors = new ArrayList<>();

        if (!checkRequiredFieldsPresent(request)) {
            log.error("Inside validation Exception as required fields not present");
            errors.add(ErrorCodeConstants.REQUIRED_FIELDS_MISSING);
        }

        if (!checkFieldsFormat(request)) {
            log.error("Inside validation Exception for invalid format");
            errors.add(ErrorCodeConstants.INCORRECT_FORMAT);
        }
        return errors;
    }

    static void sanitizeRequest(ContributionSummaryRequest request) {
        request.setEmployerGroupId(StringUtil.sanitize(request.getEmployerGroupId()));
        request.setRestrictToFunded(StringUtil.sanitize(request.getRestrictToFunded()));
        request.setDateFrom(StringUtil.sanitize(request.getDateFrom()));
        request.setDateTo(StringUtil.sanitize(request.getDateTo()));
        request.setRequestId(StringUtil.sanitize(request.getRequestId()));
    }

    static boolean checkRequiredFieldsPresent(ContributionSummaryRequest request) {
        var maxNoOfFiles = request.getMaxNumberOfFiles();
        var dateFrom = DateUtil.parse(request.getDateFrom(), ContributionInfoConstants.DATE_FORMAT);
        var dateTo = DateUtil.parse(request.getDateTo(), ContributionInfoConstants.DATE_FORMAT);

        if (StringUtil.isEmpty(request.getEmployerGroupId())
                || StringUtil.isEmpty(request.getRestrictToFunded())) {
            return false;
        }
        if ((dateFrom == null || dateTo == null) && (maxNoOfFiles == null || maxNoOfFiles == 0)) {
            return false;
        }
        return (dateFrom == null || dateTo != null) && (dateTo == null || dateFrom != null);
    }

    static boolean checkFieldsFormat(ContributionSummaryRequest request) {

        var maxNoOfFiles = request.getMaxNumberOfFiles();
        var dateFrom = DateUtil.parse(request.getDateFrom(), ContributionInfoConstants.DATE_FORMAT);
        var dateTo = DateUtil.parse(request.getDateTo(), ContributionInfoConstants.DATE_FORMAT);

        if ((!StringUtil.isEmpty(request.getEmployerGroupId())
                        && request.getEmployerGroupId().length()
                                > ContributionInfoConstants.CONTRB_EMPLOYER_GROUPID_LENGTH)
                || (!StringUtil.isAlphanumeric(request.getEmployerGroupId()))
                || (!StringUtil.isEmpty(request.getRestrictToFunded())
                        && request.getRestrictToFunded().length()
                                > ContributionInfoConstants
                                        .CONTRB_RESTRICT_TO_FUND_INDICATOR_LENGTH)
                || (!StringUtil.isAlphanumeric(request.getRestrictToFunded()))
                || (!((request.getRestrictToFunded().equalsIgnoreCase(YES))
                        || (request.getRestrictToFunded().equalsIgnoreCase(NO))))) {
            return false;
        }

        if (dateFrom != null && dateTo != null && dateFrom.getTime() > dateTo.getTime()) {
            return false;
        }
        if (maxNoOfFiles != null
                && (maxNoOfFiles < 0
                        || maxNoOfFiles.toString().length()
                                > ContributionInfoConstants.CONTRB_MAX_NUMBER_OF_FILES_LENGTH)) {
            return false;
        }

        return request.getRequestId() == null
                || request.getRequestId().length() <= ContributionInfoConstants.REQUEST_ID_LENGTH;
    }
}
