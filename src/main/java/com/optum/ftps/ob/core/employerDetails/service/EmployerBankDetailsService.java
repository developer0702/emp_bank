package com.optum.ftps.ob.core.employerDetails.service;

import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsRequest;
import com.optum.ftps.ob.core.employerDetails.model.v1.UpdateEmpBankDetailsResponse;

public interface EmployerBankDetailsService {

    UpdateEmpBankDetailsResponse updateEmployerBankDetails(UpdateEmpBankDetailsRequest request);
}
