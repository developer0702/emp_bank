package com.optum.ftps.ob.core.employerDetails.controller;

import com.optum.ftps.ob.core.employerDetails.api.v1.EmployerBankDetailsApi;
import com.optum.ftps.ob.core.employerDetails.exceptions.ValidationException;
import com.optum.ftps.ob.core.employerDetails.mapper.EmployerBankDetailsMapper;
import com.optum.ftps.ob.core.employerDetails.mapper.EmployerBankDetailsResponseMapper;
import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsRequest;
import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsResponse;
import com.optum.ftps.ob.core.employerDetails.service.EmployerBankDetailsService;
import com.optum.ftps.ob.core.employerDetails.service.ExceptionService;
import com.optum.ftps.ob.core.employerDetails.validator.EmployerBankDetailsValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmployerBankDetailsController implements EmployerBankDetailsApi {
    private final EmployerBankDetailsService employerBankDetailsService;
    private final EmployerBankDetailsMapper employerBankDetailsMapper;

    private final EmployerBankDetailsResponseMapper employerBankDetailsResponseMapper;
    private final EmployerBankDetailsValidator employerBankDetailsValidator;

    private final ExceptionService exceptionService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return EmployerBankDetailsApi.super.getRequest();
    }

    @Override
    public ResponseEntity<UpdateEmpBankDetailsResponse> updEmployerBankDetails(
            UpdateEmpBankDetailsRequest request) throws ValidationException {
        log.debug("Received request to update employer bank details: {}", request);

        Set<Integer> errorCodes = employerBankDetailsValidator.validateEmployerBankDetails(request);
        exceptionService.handleValidationError(errorCodes);
        var requestDTO = employerBankDetailsMapper.map(request);
        var responseDTO = employerBankDetailsService.updateEmployerBankDetails(requestDTO);
        var response = employerBankDetailsResponseMapper.map(responseDTO);
        log.debug("Employer bank details updated: {}", responseDTO);
        return ResponseEntity.ok(response);
    }
}
