package com.optum.ftps.ob.core.employerDetails.controller;

import com.optum.ftps.ob.core.employerDetails.api.v1.EmployerDetailsApi;
import com.optum.ftps.ob.core.employerDetails.mapper.EmployerDetailsResponseMapper;
import com.optum.ftps.ob.core.employerDetails.model.v1.EmployerDetailsResponse;
import com.optum.ftps.ob.core.employerDetails.service.EmployerDetailsService;
import com.optum.ftps.ob.core.employerDetails.util.StringUtil;
import com.optum.ftps.ob.core.employerDetails.validator.EmployerDetailsValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmployerDetailsController implements EmployerDetailsApi {
  private final EmployerDetailsValidator employerDetailsValidator;
  private final EmployerDetailsService employerDetailsService;
  private final EmployerDetailsResponseMapper employerDetailsResponseMapper;

  @Override
  public ResponseEntity<EmployerDetailsResponse> getEmployerDetailsById(String employerGroupId) {
    if (employerGroupId != null) {
      String empGroupId = StringUtil.sanitize(employerGroupId);
      log.debug("empId", empGroupId);
      var errors = employerDetailsValidator.validateEmployerDetailsById(empGroupId);
      // if (!errors.isEmpty()) {
      // log.debug("Validation failed for employer group id: {}", empGroupId);
      // exceptionService.handleValidationError(errors);
      // }
      var employerDetailsResponseDTO = employerDetailsService.getEmployerDetails(empGroupId, true);
      var response =
          employerDetailsResponseMapper.employerDetailsResponse(employerDetailsResponseDTO);
      return new ResponseEntity<EmployerDetailsResponse>(response, HttpStatus.OK);
    } else {
      log.debug("Employer group id is null");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  public ResponseEntity<EmployerDetailsResponse> getEmployerDetailsByName(
      String employerGroupName) {
    if (employerGroupName != null) {
      String empGroupName = StringUtil.sanitize(employerGroupName);
      log.debug("empName", empGroupName);
      var errors = employerDetailsValidator.validateEmployerDetailsByName(empGroupName);
      // if (!errors.isEmpty()) {
      // log.debug("Validation failed for employer group name: {}", empGroupName);
      // exceptionService.handleValidationError(errors);
      // }
      var employerDetailsResponseDTO =
          employerDetailsService.getEmployerDetails(empGroupName, false);
      var response =
          employerDetailsResponseMapper.employerDetailsResponse(employerDetailsResponseDTO);
      return new ResponseEntity<EmployerDetailsResponse>(response, HttpStatus.OK);
    } else {
      log.debug("Employer group name is null");
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
