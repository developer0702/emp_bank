package com.optum.ftps.ob.core.contribution.validator;

import com.optum.ftps.ob.core.contribution.constants.ErrorCodeConstants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContributionFileTransactionValidator {

    public List<Integer> validateContributionFileTransaction(String contributionFilePortalId) {
        log.info(
                "Validating contribution file transaction" + " for contribution file portal id: {}",
                contributionFilePortalId);
        List<Integer> errors = new ArrayList<>();
        if (Objects.isNull(contributionFilePortalId)
                || checkFieldsPresent(contributionFilePortalId)) {
            log.debug("Inside validation file transaction checking field is empty");
            errors.add(ErrorCodeConstants.REQUIRED_FIELDS_MISSING);
        }
        if (checkFormat(contributionFilePortalId)) {
            errors.add(ErrorCodeConstants.INCORRECT_FORMAT);
        }
        return errors;
    }

    boolean checkFieldsPresent(String contributionFilePortalId) {
        return contributionFilePortalId.trim().isEmpty();
    }

    boolean checkFormat(String contributionFilePortalId) {
        return !contributionFilePortalId.matches("\\d+");
    }
}
