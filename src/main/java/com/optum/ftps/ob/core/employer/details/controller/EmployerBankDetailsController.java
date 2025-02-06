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
            @Valid @RequestBody UpdateEmpBankDetailsRequest request) {
        log.debug("Received request to update employer bank details: {}", request);

        var requestDTO = employerBankDetailsMapper.map(request);
        var responseDTO = employerBankDetailsService.updateEmployerBankDetails(requestDTO);

        log.debug("Employer bank details updated: {}", responseDTO);
        return ResponseEntity.ok(null);
    }
}
