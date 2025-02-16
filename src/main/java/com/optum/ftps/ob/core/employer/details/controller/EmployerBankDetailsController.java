package com.optum.ftps.ob.core.employer.details.controller;

import com.optum.ftps.ob.core.employer.details.api.v1.EmployerBankDetailsApi;
import com.optum.ftps.ob.core.employer.details.exceptions.ValidationException;
import com.optum.ftps.ob.core.employer.details.mapper.EmployerBankDetailsMapper;
import com.optum.ftps.ob.core.employer.details.mapper.EmployerBankDetailsResponseMapper;
import com.optum.ftps.ob.core.employer.details.model.v1.UpdateEmpBankDetailsRequest;
import com.optum.ftps.ob.core.employer.details.model.v1.UpdateEmpBankDetailsResponse;
import com.optum.ftps.ob.core.employer.details.service.EmployerBankDetailsService;
import com.optum.ftps.ob.core.employer.details.validator.EmployerBankDetailsValidator;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
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

        // Validate request
        Set<Integer> errors = employerBankDetailsValidator.validateEmployerBankDetails(request);
        if (!errors.isEmpty()) {
            log.error("Validation failed for update employer bank details request: {}", errors);
            return ResponseEntity.badRequest()
                    .body(
                            UpdateEmpBankDetailsResponse.builder()
                                    .status("FAILURE")
                                    .message("Validation failed: " + errors)
                                    .build());
        }

        try {
            // Mapping request
            var requestDTO = employerBankDetailsMapper.map(request);

            // Calling service
            var responseDTO = employerBankDetailsService.updateEmployerBankDetailsServ(requestDTO);

            // Mapping response
            var response = employerBankDetailsResponseMapper.map(responseDTO);

            log.debug("Employer bank details updated successfully: {}", responseDTO);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error(
                    "Unexpected error occurred while updating employer bank details: {}",
                    e.getMessage(),
                    e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            UpdateEmpBankDetailsResponse.builder()
                                    .status("FAILURE")
                                    .message(
                                            "An unexpected error occurred. Please try again later.")
                                    .build());
        }
    }
}
