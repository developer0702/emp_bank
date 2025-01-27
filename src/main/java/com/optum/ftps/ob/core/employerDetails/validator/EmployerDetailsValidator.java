package com.optum.ftps.ob.core.employerDetails.validator;

import com.optum.ftps.ob.core.constants.EmployerDetailsConstants;
import com.optum.ftps.ob.core.constants.ErrorCodeConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
    if (checkFormat(empGroupId, true)) {
      errors.add(ErrorCodeConstants.INCORRECT_FORMAT);
    }
    return errors;
  }

  public List<Integer> validateEmployerDetailsByName(String empGroupName) {
    log.info("Validating employer details" + " for employer group name: {}", empGroupName);
    List<Integer> errors = new ArrayList<>();
    if (Objects.isNull(empGroupName) || checkFieldsPresent(empGroupName)) {
      errors.add(ErrorCodeConstants.REQUIRED_FIELDS_MISSING);
    }
    if (checkFormat(empGroupName, false)) {
      errors.add(ErrorCodeConstants.INCORRECT_FORMAT);
    }
    return errors;
  }

  boolean checkFieldsPresent(String searchField) {
    return searchField.trim().isEmpty();
  }

  boolean checkFormat(String searchField, boolean isEmpGroupId) {
    if (isEmpGroupId) {
      return searchField.trim().length() > EmployerDetailsConstants.EMPLOYER_GROUPNUMBER_LENGTH;
    }
    return searchField.trim().length() > EmployerDetailsConstants.EMPLOYER_GROUPNAME_LENGTH;
  }
}
