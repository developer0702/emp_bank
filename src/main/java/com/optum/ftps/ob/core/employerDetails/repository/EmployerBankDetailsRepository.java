package com.optum.ftps.ob.core.employerDetails.repository;

import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailsRequestDTO;

public interface EmployerBankDetailsRepository {

    EmployerBankDetailsRequestDTO updateEmployerBankDetails(
            EmployerBankDetailsRequestDTO employerBankDetailsRequest);
}
