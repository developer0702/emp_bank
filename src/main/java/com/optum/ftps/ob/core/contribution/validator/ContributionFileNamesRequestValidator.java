package com.optum.ftps.ob.core.contribution.validator;

import com.optum.ftps.ob.core.contribution.constants.ErrorCodeConstants;
import com.optum.ftps.ob.core.contribution.exceptions.ValidationException;
import com.optum.ftps.ob.core.contribution.service.ExceptionService;
import com.optum.ftps.ob.core.contribution.util.StringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ContributionFileNamesRequestValidator {
    private final ExceptionService exceptionService;

    public List<String> validate(List<String> contributionFilePortalIds) {
        if (contributionFilePortalIds == null || contributionFilePortalIds.isEmpty()) {
            log.error("Contribution file portal IDs is empty");
            var error = exceptionService.getError(ErrorCodeConstants.REQUIRED_FIELDS_MISSING);
            throw new ValidationException(List.of(error));
        }
        var sanitizedIds = new ArrayList<String>();
        for (var id : contributionFilePortalIds) {
            var sanitizedId = StringUtil.sanitize(id);
            if (!sanitizedId.matches("\\d+") || sanitizedId.length() > 10) {
                log.error("Contribution file portal ID {} is not valid", sanitizedId);
                var error = exceptionService.getError(ErrorCodeConstants.INCORRECT_FORMAT);
                throw new ValidationException(List.of(error));
            }
            sanitizedIds.add(sanitizedId);
        }
        log.debug("Validated contribution file names for {}", sanitizedIds);
        return sanitizedIds;
    }
}
