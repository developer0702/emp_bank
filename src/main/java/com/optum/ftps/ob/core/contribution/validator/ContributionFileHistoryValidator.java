package com.optum.ftps.ob.core.contribution.validator;

import com.optum.ftps.ob.core.contribution.constants.ContributionInfoConstants;
import com.optum.ftps.ob.core.contribution.constants.ErrorCodeConstants;
import com.optum.ftps.ob.core.contribution.util.DateUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContributionFileHistoryValidator {
    public List<Integer> validateContributionFileHistory(
            String customerPolicyNumber, String effectiveTransactionDate) {
        log.info(
                "Validating contribution file history for customer policy number: {} and effective"
                        + " transaction date: {}",
                customerPolicyNumber,
                effectiveTransactionDate);

        List<Integer> errors = new ArrayList<>();

        if (checkFieldsPresent(customerPolicyNumber)) {
            log.debug("Inside validation file history checking field is empty");
            errors.add(ErrorCodeConstants.REQUIRED_FIELDS_MISSING);
        }

        if (!checkFieldFormat(customerPolicyNumber, effectiveTransactionDate)) {
            log.debug("Inside validation file history checking incorrect format");
            errors.add(ErrorCodeConstants.INCORRECT_FORMAT);
        }
        if (customerPolicyNumber.length() > 12) {
            log.debug("Inside validation file history checking length incorrect format");
            errors.add(ErrorCodeConstants.INCORRECT_FORMAT);
        }
        return errors;
    }

    private boolean checkFieldsPresent(String customerPolicyNumber) {
        return (customerPolicyNumber.trim().isEmpty());
    }

    private boolean checkFieldFormat(String customerPolicyNumber, String effectiveTransactionDate) {
        if (customerPolicyNumber == null) {
            return false;
        }

        if (effectiveTransactionDate.length() > 10) {
            return false;
        }

        return !Objects.isNull(
                DateUtil.parse(effectiveTransactionDate, ContributionInfoConstants.DATE_FORMAT));
    }
}
