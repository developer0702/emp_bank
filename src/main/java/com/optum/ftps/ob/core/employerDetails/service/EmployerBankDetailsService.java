package com.optum.ftps.ob.core.employerDetails.service;

import com.optum.ftps.ob.core.employerDetails.dtos.EmployerBankDetailDTO;
import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsResponse;

public interface EmployerBankDetailsService {

    UpdateEmpBankDetailsResponse updateEmployerBankDetails(
            EmployerBankDetailDTO employerBankDetailDTO);
}
