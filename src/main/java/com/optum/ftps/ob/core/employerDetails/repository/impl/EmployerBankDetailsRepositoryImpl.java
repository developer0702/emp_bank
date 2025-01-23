package com.optum.ftps.ob.core.employerDetails.repository.impl;

import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailDTO;
import com.optum.ftps.ob.core.employerDetails.repository.EmployerBankDetailsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class EmployerBankDetailsRepositoryImpl implements EmployerBankDetailsRepository {

    @Override
    public EmployerBankDetailDTO updateEmployerBankDetails(
            EmployerBankDetailDTO employerBankDetailDTO) {
        return employerBankDetailDTO;
    }
}
