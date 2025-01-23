package com.optum.ftps.ob.core.employerDetails.service.impl;

import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsRequest;
import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsResponse;
import com.optum.ftps.ob.core.employerDetails.service.EmployerBankDetailsService;
import com.optum.ftps.ob.core.employerDetails.validator.EmployerDetailsValidator;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployerBankDetailsServiceImpl implements EmployerBankDetailsService {

    private EmployerDetailsValidator empBankDetailsValidator;

    @Override
    public UpdateEmpBankDetailsResponse updateEmployerBankDetails(
            UpdateEmpBankDetailsRequest request) {
        log.info("Method Start ********** updEmployerBankDetails()::", request);

        return null;
    }
}
