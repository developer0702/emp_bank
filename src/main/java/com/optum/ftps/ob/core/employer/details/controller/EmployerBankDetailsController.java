package com.optum.ftps.ob.core.employer.details.controller;

import com.optum.ftps.ob.core.employer.details.api.v1.EmployerBankDetailsApi;
import com.optum.ftps.ob.core.employer.details.mapper.EmployerBankDetailsMapper;
import com.optum.ftps.ob.core.employer.details.mapper.EmployerBankDetailsResponseMapper;
import com.optum.ftps.ob.core.employer.details.model.v1.UpdateEmpBankDetailsRequest;
import com.optum.ftps.ob.core.employer.details.model.v1.UpdateEmpBankDetailsResponse;
import com.optum.ftps.ob.core.employer.details.service.EmployerBankDetailsService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmployerBankDetailsController implements EmployerBankDetailsApi {
    private final EmployerBankDetailsService employerBankDetailsService;
    private final EmployerBankDetailsMapper employerBankDetailsMapper;
    private final EmployerBankDetailsResponseMapper employerBankDetailsResponseMapper;

    @Override
    public ResponseEntity<UpdateEmpBankDetailsResponse> updEmployerBankDetails(
            @Valid @RequestBody UpdateEmpBankDetailsRequest request) throws ValidationException {
        log.debug("Received request to update employer bank details: {}", request);

        var requestDTO = employerBankDetailsMapper.map(request);
        var responseDTO = employerBankDetailsService.updateEmployerBankDetails(requestDTO);

        log.debug("Employer bank details updated: {}", responseDTO);
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<UpdateEmpBankDetailsResponse> addEmployerBankAccountDetails(
            @Valid @RequestBody UpdateEmpBankDetailsRequest request) {
        log.debug("Received request to add employer bank account details: {}", request);

        /*Set<String> errors = employerBankDetailsValidator.validateAddEmpBankDetails(request);
        if (!errors.isEmpty()) {
            log.error("Validation failed for add employer bank account details request: {}", errors);
            return ResponseEntity.badRequest().build();
        }*/

        var requestDTO = employerBankDetailsMapper.map(request);
        var responseDTO = employerBankDetailsService.addEmployerBankDetails(requestDTO);
        var response = employerBankDetailsResponseMapper.map(responseDTO);

        // log.debug("Employer bank account details added: {}", responseDTO);
        return ResponseEntity.ok(response);
    }
}
