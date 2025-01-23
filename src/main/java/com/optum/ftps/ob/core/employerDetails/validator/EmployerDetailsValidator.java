package com.optum.ftps.ob.core.employerDetails.validator;

import com.optum.ftps.ob.core.constants.EmployerDetailsConstants;
import com.optum.ftps.ob.core.constants.ErrorCodeConstants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployerDetailsValidator {

    public List<Integer> validateEmployerDetailsById(String empGroupId) {
        log.info("Validating employer details" + " for employer group id: {}", empGroupId);
        List<Integer> errors = new ArrayList<>();
        if (Objects.isNull(empGroupId) || checkFieldsPresent(empGroupId)) {
            errors.add(ErrorCodeConstants.REQUIRED_FIELDS_MISSING);
        }
        if (checkFormat(empGroupId)) {
            errors.add(ErrorCodeConstants.INCORRECT_FORMAT);
        }
        return errors;
    }

    boolean checkFieldsPresent(String empGroupId) {
        return empGroupId.trim().isEmpty();
    }

    boolean checkFormat(String empGroupId) {
        return empGroupId.trim().length() > EmployerDetailsConstants.EMPLOYER_GROUPNUMBER_LENGTH;
    }
}
