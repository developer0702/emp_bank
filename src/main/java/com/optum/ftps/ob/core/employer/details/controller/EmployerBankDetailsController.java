package com.optum.ftps.ob.core.employer.details.controller;

import com.optum.ftps.ob.core.employer.details.exceptions.ValidationException;
import com.optum.ftps.ob.core.employer.details.mapper.EmployerBankDetailsMapper;
import com.optum.ftps.ob.core.employer.details.mapper.EmployerBankDetailsResponseMapper;
import com.optum.ftps.ob.core.employer.details.service.EmployerBankDetailsService;
import com.optum.ftps.ob.core.employer.details.validator.EmployerBankDetailsValidator;
import com.optum.ftps.ob.core.employerDetails.api.v1.EmployerBankDetailsApi;
import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsRequest;
import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsResponse;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmployerBankDetailsController implements EmployerBankDetailsApi {
    private final EmployerBankDetailsService employerBankDetailsService;
    private final EmployerBankDetailsValidator employerBankDetailsValidator;
    private final EmployerBankDetailsMapper employerBankDetailsMapper;
    private final EmployerBankDetailsResponseMapper employerBankDetailsResponseMapper;

    @Override
    public ResponseEntity<UpdateEmpBankDetailsResponse> updEmployerBankDetails(
            @Valid @RequestBody UpdateEmpBankDetailsRequest request) throws ValidationException {
        log.debug("Received request to update employer bank details: {}", request);

        Set<Integer> errors = employerBankDetailsValidator.validateEmployerBankDetails(request);
        if (!errors.isEmpty()) {
            log.error("Validation failed for update employer bank details request: {}", errors);
            return ResponseEntity.badRequest().build();
        }

        var requestDTO = employerBankDetailsMapper.map(request);
        var responseDTO = employerBankDetailsService.updateEmployerBankDetails(requestDTO);
        var response = employerBankDetailsResponseMapper.map(responseDTO);

        log.debug("Employer bank details updated: {}", responseDTO);
        return ResponseEntity.ok(response);
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
