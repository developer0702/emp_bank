<<<<<<< HEAD

=======
>>>>>>> e97d4c697cf38459132451a3bed2130c626e38f4
package com.optum.ftps.ob.core.employerDetails.controller;

import com.optum.ftps.ob.core.employerDetails.api.v1.EmployerBankDetailsApi;
import com.optum.ftps.ob.core.employerDetails.mapper.EmployerBankDetailsMapper;
import com.optum.ftps.ob.core.employerDetails.mapper.EmployerBankDetailsResponseMapper;
import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsRequest;
import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsResponse;
import com.optum.ftps.ob.core.employerDetails.service.EmployerBankDetailsService;
<<<<<<< HEAD
import com.optum.ftps.ob.core.employerDetails.validator.EmployerBankDetailsValidator;

import jakarta.validation.Valid;
=======

import jakarta.validation.Valid;

>>>>>>> e97d4c697cf38459132451a3bed2130c626e38f4
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmployerBankDetailsController implements EmployerBankDetailsApi {
<<<<<<< HEAD

 private final EmployerBankDetailsValidator employerBankDetailsValidator;
 private final EmployerBankDetailsMapper employerBankDetailsMapper;
 private final EmployerBankDetailsService employerBankDetailsService;
 private final EmployerBankDetailsResponseMapper employerBankDetailsResponseMapper;

 @Override
 public ResponseEntity<UpdateEmpBankDetailsResponse> updateEmployerBankDetails(
         @Valid @RequestBody UpdateEmpBankDetailsRequest request) {
  log.debug("Received request to update employer bank details : {}", request);



  var requestDTO = employerBankDetailsMapper.map(request);
  var responseDTO = employerBankDetailsService.updateEmployerBankDetails(requestDTO);
   var response = employerBankDetailsResponseMapper.map(responseDTO);

  log.debug("Employer bank details updated : {}", responseDTO);
  return ResponseEntity.ok(null);
 }
}
=======
    private final EmployerBankDetailsService employerBankDetailsService;
    // private final EmployerBankDetailsValidator employerBankDetailsValidator;
    private final EmployerBankDetailsMapper employerBankDetailsMapper;
    private final EmployerBankDetailsResponseMapper employerBankDetailsResponseMapper;

    @Override
    public ResponseEntity<UpdateEmpBankDetailsResponse> updEmployerBankDetails(
            @Valid @RequestBody UpdateEmpBankDetailsRequest request) {
        log.debug("Received request to update employer bank details: {}", request);

        /*Set<String> errors = employerBankDetailsValidator.validateUpdateEmpBankDetails(request);
        if (!errors.isEmpty()) {
            log.error("Validation failed for update employer bank details request: {}", errors);
            return ResponseEntity.badRequest().build();
        }*/

        var requestDTO = employerBankDetailsMapper.map(request);
        var responseDTO = employerBankDetailsService.updateEmployerBankDetails(requestDTO);
        var response = employerBankDetailsResponseMapper.map(responseDTO);

        log.debug("Employer bank details updated: {}", responseDTO);
        return ResponseEntity.ok(null);
    }
}
>>>>>>> e97d4c697cf38459132451a3bed2130c626e38f4
